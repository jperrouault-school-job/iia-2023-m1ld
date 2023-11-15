using commentaire_service.Models;
using Microsoft.EntityFrameworkCore;

namespace commentaire_service.Context;

public class CommentaireContext : DbContext
{
    public DbSet<Commentaire>? Commentaires { get; set; }
    public DbSet<Produit>? Produits { get; set; }

    public CommentaireContext(DbContextOptions<CommentaireContext> options) : base(options)
    {

    }
}