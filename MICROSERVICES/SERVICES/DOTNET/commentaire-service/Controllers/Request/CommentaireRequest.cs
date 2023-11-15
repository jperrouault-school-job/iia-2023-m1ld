namespace commentaire_service.Controllers.Request;

public class CommentaireRequest
{
    public string? Texte { get; set; }
    public int Note { get; set; }
    public string ProduitId { get; set; }
}