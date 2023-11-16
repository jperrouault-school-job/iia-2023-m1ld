namespace commentaire_service.Event;

public class CommentaireValidatedEvent
{
    public string CommentaireId { get; set; }
    public string ProduitId { get; set; }
}