using commentaire_service.EventConsumer;
using Steeltoe.Connector.RabbitMQ;
using Steeltoe.Messaging.RabbitMQ.Config;
using Steeltoe.Messaging.RabbitMQ.Extensions;

namespace commentaire_service.Startup;

public static class RabbitMqStartup
{
    public static IServiceCollection UseRabbitConfiguration(this IServiceCollection services, ConfigurationManager configManager)
    {
        // Configuration générale
        services.AddRabbitMQConnection(configManager);
        services.AddRabbitServices(true);
        services.AddRabbitAdmin();
        services.AddRabbitTemplate();

        // Configuration de l'Exchange du service
        services.AddRabbitExchange("ms.commentaire", ExchangeType.TOPIC);

        // Déclaration des Queues
        services.AddRabbitQueue("ms.commentaire.commentaire.validate");
        services.AddRabbitQueue("ms.commentaire.commentaire.reject");

        // Configuration du service qui captera les évènements reçus
        services.AddSingleton<CommentaireValidatedOrRejectedEventConsumer>();
        services.AddRabbitListeners<CommentaireValidatedOrRejectedEventConsumer>();

        return services;
    }
}