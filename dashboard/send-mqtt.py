import paho.mqtt.client as mqtt
import random

#point="capteur,numero=4 intensity=10"

MQTT_SERVER = "127.0.0.1"
MQTT_PORT = 1883
MQTT_TOPIC = "capteur"

client = mqtt.Client() #Création client MQTT

try :
    client.connect(MQTT_SERVER,MQTT_PORT)
except :
    print("Connection to MQTT server failed")
    exit()


for i in range(66) :
    point = f"capteur,numero={i} intensity={random.randint(0, 9)}"
    try:
        client.publish(MQTT_TOPIC,point)
    except:
        print("Publishing Error")
        exit()

client.disconnect()