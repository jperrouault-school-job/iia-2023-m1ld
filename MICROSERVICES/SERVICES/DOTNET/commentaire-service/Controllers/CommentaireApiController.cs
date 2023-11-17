using commentaire_service.Context;
using commentaire_service.Controllers.Request;
using commentaire_service.Models;
using commentaire_service.Models.Enums;
using commentaire_service.Command;
using Microsoft.AspNetCore.Mvc;
using Steeltoe.Messaging.RabbitMQ.Core;

namespace commentaire_service.Controllers;

[ApiController]
[Route("/api/commentaire")]
public class CommentaireApiController : ControllerBase
{
    private readonly ILogger<CommentaireApiController> _logger;
    private readonly CommentaireContext _commentaireContext;
    private readonly RabbitTemplate _rabbitTemplate;

    public CommentaireApiController(ILogger<CommentaireApiController> logger,
                                    CommentaireContext commentaireContext,
                                    IHttpClientFactory httpClientFactory, 
                                    RabbitTemplate rabbitTemplate)
    {
        _logger = logger;
        _commentaireContext = commentaireContext;
        _rabbitTemplate = rabbitTemplate;
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
