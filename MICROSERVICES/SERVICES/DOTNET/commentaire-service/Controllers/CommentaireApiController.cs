using commentaire_service.Context;
using commentaire_service.Controllers.Request;
using commentaire_service.Models;
using commentaire_service.Models.Enums;
using commentaire_service.Command;
using Microsoft.AspNetCore.Mvc;
using Polly;
using Steeltoe.Messaging.RabbitMQ.Core;

namespace commentaire_service.Controllers;

[ApiController]
[Route("/api/commentaire")]
public class CommentaireApiController : ControllerBase
{
    private readonly ILogger<CommentaireApiController> _logger;
    private readonly CommentaireContext _commentaireContext;
    private readonly IHttpClientFactory _httpClientFactory;
    private readonly RabbitTemplate _rabbitTemplate;

    public CommentaireApiController(ILogger<CommentaireApiController> logger,
                                    CommentaireContext commentaireContext,
                                    IHttpClientFactory httpClientFactory, 
                                    RabbitTemplate rabbitTemplate)
    {
        _logger = logger;
        _commentaireContext = commentaireContext;
        _httpClientFactory = httpClientFactory;
        _rabbitTemplate = rabbitTemplate;
    }

    [HttpGet("{id}")]
    public async Task<IActionResult> FindById([FromRoute] string id)
    {
        Commentaire commentaire = _commentaireContext.Commentaires.First(c => c.Id == id);
        HttpClient httpClient = _httpClientFactory.CreateClient("produit-service");
        var fallbackForAnyException = Policy<string>
            .Handle<Exception>()
            .FallbackAsync(async (ct) => "No name");

        string produitNom = await fallbackForAnyException.ExecuteAsync(async () => {
            return await httpClient.GetStringAsync("/api/produit/" + commentaire.ProduitId + "/get-name");
        });

        CommentaireResponse response = new CommentaireResponse
        {
            Note = (commentaire.NoteQualite + commentaire.NoteQualitePrix + commentaire.NoteFacilite) / 3,
            Texte = commentaire.Texte,
            ProduitId = commentaire.ProduitId,
            ProduitNom = produitNom
        };

        return Ok(response);
    }

    [HttpGet("by-produit-id/{produitId}")]
    public IActionResult FindAllByProduitId([FromRoute] string produitId)
    {
        IEnumerable<Commentaire> commentaires = _commentaireContext.Commentaires.Where(c => c.ProduitId == produitId &&  c.Etat == CommentaireEtat.OK);
        IEnumerable<CommentaireResponse> resp = commentaires.Select(c => new CommentaireResponse
        {
            Texte = c.Texte,
            Note = (c.NoteQualite + c.NoteQualitePrix + c.NoteFacilite) / 3
        });

        return Ok(resp);
    }

    [HttpGet("note/by-produit-id/{produitId}")]
    public IActionResult GetNoteByProduitId([FromRoute] string produitId)
    {
        IEnumerable<Commentaire> commentaires = _commentaireContext.Commentaires.Where(c => c.ProduitId == produitId);

        if (commentaires.Count() == 0) {
            return Ok(-1);
        }

        return Ok(commentaires.Select(c => (c.NoteQualite + c.NoteQualitePrix + c.NoteFacilite) / 3).Average());
    }

    [HttpPost]
    public IActionResult Add(CommentaireRequest request)
    {
        Commentaire commentaire = new Commentaire
        {
            Texte = request.Texte,
            NoteQualite = request.NoteQualite,
            NoteQualitePrix = request.NoteQualitePrix,
            NoteFacilite = request.NoteFacilite,
            ProduitId = request.ProduitId,
            Etat = CommentaireEtat.PENDING
        };

        _commentaireContext.Commentaires.Add(commentaire);
        _commentaireContext.SaveChanges();

        _rabbitTemplate.ConvertAndSend("ms.commentaire", "commentaire.create", new CreateCommentaireCommand
        {
            CommentaireId = commentaire.Id,
            ProduitId = request.ProduitId
        });

        return Ok(commentaire.Id);
    }
}
