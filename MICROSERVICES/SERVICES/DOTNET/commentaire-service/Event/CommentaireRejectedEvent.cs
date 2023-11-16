namespace commentaire_service.Event;

public class CommentaireRejectedEvent
{
    public string CommentaireId { get; set; }
    public string ProduitId { get; set; }
}