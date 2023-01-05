import paho.mqtt.client as mqtt
import serial

# UART 
PORT = 'COM3'
UART_SPEED = 9600
ser = serial.Serial()

isSerialOpen = False

# Function to initialize the UART
def initUart(port, speed):

    global isSerialOpen

    ser.port = port
    ser.baudrate = speed
    ser.bytesize = serial.EIGHTBITS  # number of bits per bytes
    ser.parity = serial.PARITY_NONE  # set parity check: no parity
    ser.stopbits = serial.STOPBITS_ONE  # number of stop bits
    ser.timeout = None  # block read

    ser.xonxoff = False  # disable software flow control
    ser.rtscts = False  # disable hardware (RTS/CTS) flow control
    ser.dsrdtr = False

    # Try opening the serial port
    try:
        ser.open()
        print(f"Opening serial port: {port} SUCCESS")
        # Change the button text
        isSerialOpen = True
    except serial.SerialException:
        print(f"Serial {port} port not available")

#point="capteur,numero=4 intensity=10"

MQTT_SERVER = "127.0.0.1"
MQTT_PORT = 1883
MQTT_TOPIC = "capteur"

client = mqtt.Client() #Création client MQTT

initUart(PORT, UART_SPEED)

while True:
    if isSerialOpen :
        if ser.in_waiting:
            print("Donnée reçue")
            dataRead = ser.readline()
            print(f"Received data from the µBit via UART : {dataRead.decode()}")
            data = dataRead.decode()
            point = f"capteur,numero={data[:2]} intensity={data[3]},etat={data[2]}"

            try :
                client.connect(MQTT_SERVER,MQTT_PORT)
                print("Connection to MQTT server successful")
            except :
                print("Connection to MQTT server failed")
                exit()

            try:
                client.publish(MQTT_TOPIC,point)
                print("Sent")
            except:
                print("Publishing Error")
                exit()

            client.disconnect()
