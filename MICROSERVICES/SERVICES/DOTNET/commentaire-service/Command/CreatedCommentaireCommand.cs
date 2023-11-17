namespace commentaire_service.Command;

public class CreatedCommentaireCommand
{
    public string Id { get; set; }
    public string Texte { get; set; }
    public int Note { get; set; }
    public string ProduitId { get; set; }
}