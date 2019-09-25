#!/bin/bash

# install packages
wget -qO - https://artifacts.elastic.co/GPG-KEY-elasticsearch | sudo apt-key add -
sudo apt-get install apt-transport-https
echo "deb https://artifacts.elastic.co/packages/7.x/apt stable main" | sudo tee /etc/apt/sources.list.d/elastic-7.x.list
sudo apt-get update && sudo apt-get install elasticsearch kibana auditbeat firejail

# listen on public ips, so we can do vagrant port forwarding
sudo echo "http.host: 0.0.0.0" >> /etc/elasticsearch/elasticsearch.yml
sudo echo "server.host: 0.0.0.0" >> /etc/kibana/kibana.yml
# also make sure dashboards will be loaded
sudo echo "setup.dashboards.enabled: true" >> /etc/auditbeat/auditbeat.yml

# ensure everything starts up on reboot
sudo /bin/systemctl daemon-reload
sudo /bin/systemctl enable elasticsearch.service
sudo /bin/systemctl enable kibana.service
sudo /bin/systemctl enable auditbeat.service

# but also start it right away
sudo systemctl start elasticsearch.service
sudo systemctl start kibana.service
# let's sleep, so that elasticsearch and kibana can start up
# otherwise starting auditbeat fails after a few tries
sleep 60
sudo systemctl start auditbeat.service

