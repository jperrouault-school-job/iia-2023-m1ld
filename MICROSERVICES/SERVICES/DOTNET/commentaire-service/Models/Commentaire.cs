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

    [Column("note")]
    public int Note { get; set; }

    [Column("produit_id")]
    public string ProduitId { get; set; } = "";
}
