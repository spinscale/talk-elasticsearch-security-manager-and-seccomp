# Elasticsearch, the Java Security Manager and seccomp

This repository contains some demos for my talk about Elasticsearch, how it
leverages the Java Security Manager and how it uses seccomp to prevent forking
of processed.

You can check out the slides [over here](https://noti.st/spinscale/bHspAd/elasticsearch-securing-a-search-engine-while-maintaining-usability)
or in a slightly different and shorter version [over here](https://noti.st/spinscale/eEqdPa/security-more-than-an-operations-topic)

Also, there is a blog post about
[Seccomp in the Elastic Stack](https://www.elastic.co/blog/seccomp-in-the-elastic-stack),
that you should read.

There are three different contents in here.

1. Some security manager code samples, which have been tested with Java 13
   under osx.
1. Some snippets for showing painless sandboxing and protection features in
   `painless.snippets`
1. A vagrant virtual machine, that fires up Kibana, Elasticsearch and auditbeat
   to show seccomp violations.


## Java Security Manager Code

The code repository features a few java classes named `Sample01` up to
`Sample05` that shows security sensitive operations like reflections,
opening network sockets or executing commands. With regards to reflections
the most funny part is how to change a static final variable or make a
private variable visible using reflection.

The class `SecurityManagerSamples` configures a security manager and a
policy to allow for certain operations and allows to remove permissions
to see how an operation fails.


## Painless snippets

The file `painless.snippets` includes a couple of scripts within a search,
that execute painless scripts and will result in an error. This is merely
to illustrate a couple of caught cornercases and the execution within a
sandbox.


## Seccomp testing environment

The last demo is a vagrant machine, that is an ubuntu, which downloads
and then starts Elasticsearch, Kibana and auditbeat.

After logging into the vm and calling the script under
`/vagrant/trigger-seccomp-violation.sh` you can first call `dmesg` to
show such a violation and then open up kibana (port forwarding is
enabled) and show such a violation in the auditbeat dashboards.

If there is time, explain the `firejail` tool used in the above
mentioned shell script.

In order to start up the vagrant machine, just switch into the
`seccomp-example-vm` directory and run `vagrant up`.
