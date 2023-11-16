using Polly;

namespace commentaire_service.Startup;

public static class HttpFactoryStartup
{
    public static IHttpClientBuilder UseHttpConfiguration(this IServiceCollection services, string clientName, string url)
    {
        return services.AddHttpClient(clientName, client => {
            client.BaseAddress = new Uri(url);
        })
        .AddRandomLoadBalancer()
        .AddTransientHttpErrorPolicy(policy => policy.CircuitBreakerAsync(3, TimeSpan.FromSeconds(5)));
    }
}