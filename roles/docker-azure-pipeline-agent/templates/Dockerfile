FROM ubuntu:16.04

LABEL maintainer="rodrigothomaz@msn.com"

# dockeragent

# referência: 
# https://docs.microsoft.com/en-us/azure/devops/pipelines/agents/docker?view=azure-devops


# To make it easier for build and release pipelines to run apt-get,
# configure apt to not require confirmation (assume the -y argument by default)
ENV DEBIAN_FRONTEND=noninteractive
RUN echo "APT::Get::Assume-Yes \"true\";" > /etc/apt/apt.conf.d/90assumeyes

RUN apt-get update \
&& apt-get install -y --no-install-recommends \
        ca-certificates \
        curl \
        jq \
        git \
        iputils-ping \
        libcurl3 \
        libicu55 \
        libunwind8 \
        netcat

WORKDIR /azp

COPY ./start.sh .
RUN chmod +x start.sh

# mono

RUN apt-get update \
  && apt-get install -y binutils curl mono-devel ca-certificates-mono fsharp mono-vbnc nuget tzdata \
  && rm -rf /var/lib/apt/lists/* /tmp/*

CMD ["./start.sh"]


