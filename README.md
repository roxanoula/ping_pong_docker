# ping_pong_docker
A very simple Java client-server project to show how to communicate between two docker images.

# Buind the docker
docker build -t serverimage .
docker build -t clientimage .

# Run the docker
docker run --net=host -it -P --name server serverimage
docker run --net=host -it -P --name client clientimage
