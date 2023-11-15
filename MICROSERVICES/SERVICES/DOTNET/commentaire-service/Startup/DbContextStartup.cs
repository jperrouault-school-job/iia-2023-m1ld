using commentaire_service.Context;
using Microsoft.EntityFrameworkCore;

namespace commentaire_service.Startup;

public static class DbContextStartup
{
    public static IServiceCollection UseDbContextConfiguration(this IServiceCollection services, string connectionString)
    {
        services.AddDbContext<CommentaireContext>(options =>
            options.UseNpgsql(connectionString)
        );

        return services;
    }
}