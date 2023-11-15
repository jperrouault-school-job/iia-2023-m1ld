namespace commentaire_service.Controllers.Request;

public class CommentaireRequest
{
    public string? Texte { get; set; }
    public int NoteQualite { get; set; }
    public int NoteQualitePrix { get; set; }
    public int NoteFacilite { get; set; }
    public string ProduitId { get; set; }
}