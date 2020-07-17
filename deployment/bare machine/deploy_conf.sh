#deploy for server 1
ssh -oPort=6502 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/conf/bkenv.sh'
ssh -oPort=6502 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/conf/pulsar_env.sh'
ssh -oPort=6502 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/conf/bookkeeper.conf'
ssh -oPort=6502 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/conf/zookeeper.conf'
ssh -oPort=6502 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/conf/broker.conf'
scp -oPort=6502 -r common-settings/conf cm@47.92.80.237:/home/cm/apache-pulsar-2.6.0/
scp -oPort=6502 -r server1/conf cm@47.92.80.237:/home/cm/apache-pulsar-2.6.0/

#deploy for server 2
ssh -oPort=6503 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/conf/bkenv.sh'
ssh -oPort=6503 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/conf/pulsar_env.sh'
ssh -oPort=6503 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/conf/bookkeeper.conf'
ssh -oPort=6503 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/conf/zookeeper.conf'
ssh -oPort=6503 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/conf/broker.conf'
scp -oPort=6503 -r common-settings/conf cm@47.92.80.237:/home/cm/apache-pulsar-2.6.0/
scp -oPort=6503 -r server2/conf cm@47.92.80.237:/home/cm/apache-pulsar-2.6.0/

#deploy for server 3
ssh -oPort=6504 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/conf/bkenv.sh'
ssh -oPort=6504 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/conf/pulsar_env.sh'
ssh -oPort=6504 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/conf/bookkeeper.conf'
ssh -oPort=6504 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/conf/zookeeper.conf'
ssh -oPort=6504 cm@47.92.80.237 'rm -rf /home/cm/apache-pulsar-2.6.0/conf/broker.conf'
scp -oPort=6504 -r common-settings/conf cm@47.92.80.237:/home/cm/apache-pulsar-2.6.0/
scp -oPort=6504 -r server3/conf cm@47.92.80.237:/home/cm/apache-pulsar-2.6.0/