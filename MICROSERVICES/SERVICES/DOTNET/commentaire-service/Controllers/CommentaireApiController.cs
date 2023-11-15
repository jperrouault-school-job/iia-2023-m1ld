using commentaire_service.Context;
using commentaire_service.Controllers.Request;
using commentaire_service.Models;
using Microsoft.AspNetCore.Mvc;

namespace commentaire_service.Controllers;

[ApiController]
[Route("/api/commentaire")]
public class CommentaireApiController : ControllerBase
{
    private readonly ILogger<CommentaireApiController> _logger;
    private readonly CommentaireContext _commentaireContext;

    public CommentaireApiController(ILogger<CommentaireApiController> logger, CommentaireContext commentaireContext)
    {
        _logger = logger;
        _commentaireContext = commentaireContext;
    }

    [HttpGet("{id}")]
    public IActionResult FindById([FromRoute] string id)
    {
        Commentaire commentaire = this._commentaireContext.Commentaires.First(c => c.Id == id);
        Produit produit = this._commentaireContext.Produits.First(p => p.Id == commentaire.ProduitId);

        CommentaireResponse response = new CommentaireResponse
        {
            Note = commentaire.Note,
            Texte = commentaire.Texte,
            ProduitId = commentaire.ProduitId,
            ProduitNom = produit.Nom
        };

        return Ok(response);
    }

    [HttpPost]
    public IActionResult Add(CommentaireRequest request)
    {
        Produit produit = this._commentaireContext.Produits.First(p => p.Id == request.ProduitId);

        if (!produit.Notable) {
            return BadRequest(false);
        }

        Commentaire commentaire = new Commentaire
        {
            Texte = request.Texte,
            Note = request.Note,
            ProduitId = request.ProduitId
        };

        this._commentaireContext.Commentaires.Add(commentaire);
        this._commentaireContext.SaveChanges();

        return Ok(commentaire.Id);
    }
}
