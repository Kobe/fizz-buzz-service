# Vagrant Deployment

There is a vagrant file available to test packaging and deployment related changes.

To run fizz-buzz-service in the vagrant box make sure that the server is packaged properly via

```../../../mvnw package```

After that you start the vagrant box via

```vagrant up```

and find the app running on http://localhost:8080/fizz-buzz/15 (as there is a port forward in the vagrant vm)