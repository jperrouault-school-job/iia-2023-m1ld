using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace commentaire_service.Models;


[Table("produit")]
public class Produit
{
    [Key]
    [Column("id")]
    public string Id { get; set; }

    [Column("nom")]
    public string Nom { get; set; } = "";

    [Column("notable")]
    public bool Notable { get; set; }
}
