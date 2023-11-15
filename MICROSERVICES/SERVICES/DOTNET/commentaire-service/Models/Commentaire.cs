using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace commentaire_service.Models;


[Table("commentaire")]
public class Commentaire
{
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    [Key]
    [Column("id")]
    public string Id { get; set; }

    [Column("texte")]
    public string Texte { get; set; } = "";

    [Column("note_qualite")]
    public int NoteQualite { get; set; }

    [Column("note_qualite_prix")]
    public int NoteQualitePrix { get; set; }

    [Column("note_facilite")]
    public int NoteFacilite { get; set; }

    [Column("produit_id")]
    public string ProduitId { get; set; } = "";
}
