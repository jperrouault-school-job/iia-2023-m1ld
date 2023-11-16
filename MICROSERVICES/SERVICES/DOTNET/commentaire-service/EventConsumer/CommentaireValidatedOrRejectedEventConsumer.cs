using commentaire_service.Context;
using commentaire_service.Models;
using commentaire_service.Models.Enums;
using commentaire_service.Event;
using Steeltoe.Messaging.RabbitMQ.Attributes;
using Steeltoe.Messaging.RabbitMQ.Core;
using Steeltoe.Messaging.RabbitMQ.Extensions;

namespace commentaire_service.EventConsumer;
public class CommentaireValidatedOrRejectedEventConsumer {
    private readonly IServiceProvider _services;

    public CommentaireValidatedOrRejectedEventConsumer(IServiceProvider services)
    {
        _services = services;
    }

    [DeclareQueueBinding(
        Name = "ms.commentaire.commentaire.validate",
        QueueName = "ms.commentaire.commentaire.validate",
        ExchangeName = "ms.commentaire",
        RoutingKey = "commentaire.validate")
    ]
    [RabbitListener(Binding = "ms.commentaire.commentaire.validate")]
     public void on(CommentaireValidatedEvent evt)
    {
        using (var scope = _services.CreateScope())
        {
            var commentaireContext = scope.ServiceProvider.GetService<CommentaireContext>();
            Commentaire commentaire = commentaireContext.Commentaires.First(c => c.Id == evt.CommentaireId);

            commentaire.Etat = CommentaireEtat.OK;
            commentaireContext.SaveChanges();
        }
    }

    [DeclareQueueBinding(
        Name = "ms.commentaire.commentaire.reject",
        QueueName = "ms.commentaire.commentaire.reject",
        ExchangeName = "ms.commentaire",
        RoutingKey = "commentaire.reject")
    ]
    [RabbitListener(Binding = "ms.commentaire.commentaire.reject")]
     public void on(CommentaireRejectedEvent evt)
    {
        using (var scope = _services.CreateScope())
        {
            var commentaireContext = scope.ServiceProvider.GetService<CommentaireContext>();
            Commentaire commentaire = commentaireContext.Commentaires.First(c => c.Id == evt.CommentaireId);

            commentaireContext.Commentaires.Remove(commentaire);
            commentaireContext.SaveChanges();
        }
    }
}