using commentaire_service.Context;
using commentaire_service.Controllers.Request;
using commentaire_service.Models;
using Microsoft.AspNetCore.Mvc;
using Polly;

namespace commentaire_service.Controllers;

[ApiController]
[Route("/api/commentaire")]
public class CommentaireApiController : ControllerBase
{
    private readonly ILogger<CommentaireApiController> _logger;
    private readonly CommentaireContext _commentaireContext;
    private readonly IHttpClientFactory _httpClientFactory;

    public CommentaireApiController(ILogger<CommentaireApiController> logger, CommentaireContext commentaireContext, IHttpClientFactory httpClientFactory)
    {
        _logger = logger;
        _commentaireContext = commentaireContext;
        _httpClientFactory = httpClientFactory;
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
        IEnumerable<Commentaire> commentaires = _commentaireContext.Commentaires.Where(c => c.ProduitId == produitId);
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
    public async Task<IActionResult> Add(CommentaireRequest request)
    {
        HttpClient httpClient = _httpClientFactory.CreateClient("produit-service");
        var fallbackForAnyException = Policy<bool>
            .Handle<Exception>()
            .FallbackAsync(async (ct) => false);

        bool isProduitNotable = await fallbackForAnyException.ExecuteAsync(async () => {
            return await httpClient.GetFromJsonAsync<bool>("/api/produit/" + request.ProduitId + "/is-notable");
        });

        if (!isProduitNotable) {
            return BadRequest(false);
        }

        Commentaire commentaire = new Commentaire
        {
            Texte = request.Texte,
            NoteQualite = request.NoteQualite,
            NoteQualitePrix = request.NoteQualitePrix,
            NoteFacilite = request.NoteFacilite,
            ProduitId = request.ProduitId
        };

        _commentaireContext.Commentaires.Add(commentaire);
        _commentaireContext.SaveChanges();

        return Ok(commentaire.Id);
    }
}
