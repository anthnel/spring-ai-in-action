# spring-ai-in-action

## TestContainers

### Configuration sous windows avec Podman Desktop

```powershell
❯ cat .\.testcontainers.properties
#Modified by Testcontainers
#Mon Dec 16 14:15:09 CET 2024
docker.client.strategy=org.testcontainers.dockerclient.NpipeSocketClientProviderStrategy
testcontainers.reuse.enable=true
docker.host=npipe://./pipe/podman-machine-default
```
