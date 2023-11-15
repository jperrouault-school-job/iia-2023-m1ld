namespace commentaire_service.Controllers.Request;

public class CommentaireResponse
{
    public string? Texte { get; set; }
    public int Note { get; set; }
    public string ProduitId { get; set; }
    public string ProduitNom { get; set; }
}