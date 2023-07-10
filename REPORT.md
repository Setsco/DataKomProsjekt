# Smart Home
This is a school project for the course IDATA2304 Computer Networks

Francisco Glomset
June 2023
Norwegian University of Science and Technology
Faculty of Natural Sciences
Department of Physics

## Abstract
The Internet of Things (IoT) has emerged as a transformative technology that enables smart devices to sense and control the environment, exchanging data over the internet. This project aims to demonstrate the application of IoT in the context of smart home automation, focusing on developing a prototype that showcases the potential benefits of this technology for society.

The project revolves around the concept of a smart home where various sensor nodes, including humidity, light, motion, and temperature, collect data and interact with actuator nodes, such as smart lights, to create an intelligent and automated living environment. The central objective is to provide a relevant and meaningful application that addresses societal challenges while leveraging the power of IoT.

To achieve this, the project employs a combination of software and hardware components. Java programming language is utilized for implementing the sensor and actuator nodes, as well as for developing the user interface and visualization using JavaFX. Communication between nodes is facilitated through the MQTT (Message Queuing Telemetry Transport) protocol, ensuring efficient and reliable data exchange.

## Introduction
The Internet of Things (IoT), which enables smart devices to perceive, monitor, and manage the environment, has completely changed how we interact with our surroundings. This technology has the potential to have a huge impact on many areas of our life, from increasing ease and security to increasing energy efficiency. In this project, we investigate the use of IoT in the context of smart home automation with an emphasis on creating a useful prototype that highlights the potential of this cutting-edge technology.

The core aim of this project is to utilize Internet of Things (IoT) to develop a sophisticated and automated living environment that effectively tackles real-world issues. The overarching goal is to conceive a smart home system that surpasses basic monitoring and control functions, with a focus on enhancing energy efficiency, optimizing living conditions, and elevating user comfort. Leveraging IoT technologies allows us to harness the potential of interconnected devices, data exchange, and intelligent decision-making, thereby creating a more sustainable and convenient living space.

The proposed smart home automation solution encompasses a network of strategically positioned sensor nodes within the residence, designed to capture and analyze essential environmental data. These sensor nodes comprise humidity, light, motion, and temperature sensors, enabling the collection of valuable information pertaining to the home's surroundings.

To ensure smooth communication and efficient data exchange between the sensor and actuator nodes, we adopt the MQTT (Message Queuing Telemetry Transport) protocol. This lightweight and streamlined protocol guarantees reliable and real-time communication, enabling prompt response and synchronization among the diverse components of the smart home system. The utilization of MQTT offers the essential flexibility and scalability required to accommodate future expansions and seamless integration with other IoT devices.

## Theory

### Java
Java is an advanced OOP language. Java programs can run on any system that has a Java Virtual Machine(JVM) installed thanks to its "write once, execute anywhere" principle. This is due to the fact that Java code is converted into platform-independent bytecode during the compilation process. After translating the bytecode the JVM executes the program on the host system

Java's syntax is comparable to those of C and C++, but it has extra capabilities like automatic memory management that make it simpler to design programs that do not contain mistakes like memory leaks. A sizable class library that offers a variety of pre-built functions and classes that may be used to carry out common programming tasks is also included in Java.[1]

### Maven
Maven is a widely adopted build automation and dependency management tool specifically designed for Java-based projects. It offers a robust and standardized framework for handling various aspects of the software development lifecycle. With Maven, developers can efficiently manage project dependencies, compile source code, create build artifacts, and execute project builds.

One of the key advantages of using Maven is its declarative configuration approach. Developers specify the project's structure, dependencies, and build settings in a single configuration file called the Project Object Model (POM). The POM file, typically named pom.xml, serves as the central source of project information and is used by Maven to orchestrate the build process.[2]

### MQTT broker
An MQTT broker, also referred to as an MQTT server or message broker, assumes a central role within an MQTT-based system. Its primary function is to serve as an intermediary entity, enabling communication and message routing between MQTT clients. When MQTT publishers (senders) transmit messages, the broker receives them and forwards them to MQTT subscribers (receivers) based on the topic hierarchy.

Upon publishing a message to a specific topic, the MQTT broker captures the message and disseminates it to all subscribed clients expressing interest in that particular topic. This decoupling of publishers and subscribers facilitates efficient and scalable message distribution across a network of devices.

The MQTT broker undertakes several responsibilities, including managing client connections, overseeing message delivery, implementing security measures such as authentication and encryption, and ensuring reliable message transfer through various Quality of Service (QoS) levels. It maintains a record of connected clients and their subscriptions, enabling seamless and dependable communication among MQTT-enabled devices.[3]

### MQTT protocol
The MQTT (Message Queuing Telemetry Transport) protocol is a lightweight messaging protocol specifically designed for efficient communication between IoT devices. It employs a broker-based architecture, wherein a central message broker acts as an intermediary between publishers (sensor nodes) and subscribers (actuator nodes or clients).

Operating over TCP/IP, MQTT utilizes a publish/subscribe messaging pattern. The message broker serves as a central hub that receives published messages from publishers and routes them to the appropriate subscribers based on their subscriptions. This decoupled communication model facilitates efficient and scalable data exchange within IoT systems.

MQTT is renowned for its low overhead and optimal utilization of network resources, making it suitable for constrained environments characterized by limited bandwidth and power resources. It employs a simple binary protocol that minimizes packet size, reduces network traffic, and ensures swift message delivery.[3]

## Method

### Requirement Gathering
The project member initially conducted thorough research to gain an understanding of the concepts and principles of IoT and smart home automation. This included studying existing solutions, identifying potential use cases, and defining the specific problem statement that the project aimed to address.

### Sensor node implementation
The project member developed the sensor and actuator nodes using Java programming language. Each sensor node (humidity, light, motion, and temperature) was programmed to collect data from the environment and simulate meaningful readings. 

### Communication and data exchange
The project member implemented the communication infrastructure using the MQTT protocol. MQTT was chosen for its lightweight nature and efficient message exchange capabilities. The sensor nodes were configured to publish data to specific topics, while the actuator node subscribed to relevant topics to receive control signals. The MQTT broker facilitated seamless communication and data exchange between the nodes.

### Data simulation and processing
Data simulation techniques were applied to generate realistic sensor readings within defined ranges. For example, random values within a specific range were generated for humidity and temperature, while motion detection was simulated based on certain conditions. Data processing algorithms were implemented to analyze the collected data and trigger appropriate actions, such as turning on the lights when motion was detected.

### User Interface and visualization
The project member used JavaFX to develop the user interface for the visualization node. The visualization node displayed real-time sensor data 

## Results

### Overall results
The project has achieved a notable success by effectively simulating realistic data for the various nodes involved. The continuous real-time transmission of data, coupled with dynamic scenarios such as the sensor node triggering lights on and off, enhances the authenticity and practicality of the simulation. Furthermore, the Visualization node offers a simplistic yet informative representation of the data, with the added advantage of real-time updates. 

### Sensor nodes
The project incorporates four distinct sensor nodes: humidity, light, motion, and temperature nodes. Each sensor node possesses its own dedicated method for simulating data. The simulated data adheres to specified minimum and maximum values, with fluctuations occurring randomly within this range. This approach ensures that the generated data realistically represents the variations typically observed in the respective sensor readings.

### Visualization node
The visualization node plays a crucial role in the project by providing real-time display of data captured by the sensor nodes. The visualization component adopts a straightforward and efficient approach, presenting the data in a simple text format. This approach allows for immediate and easily understandable interpretation of the sensor readings without the need for complex visualizations. By providing real-time updates, the visualization node enables users to promptly monitor and analyze the changing data from the sensor nodes.

### Communication
In accordance with the project requirements, MQTT broker was chosen as the communication mechanism for the sensor nodes and the visualization node. Each sensor node establishes a connection with a specific MQTT broker URL and subscribes to a designated topic. Whenever a value is modified or updated, the sensor node publishes the new value to its corresponding topic on the MQTT broker. This enables the visualization node to receive real-time data updates from the sensor nodes by subscribing to the relevant topics on the broker. The MQTT broker acts as a reliable intermediary, facilitating seamless and efficient communication between the sensor nodes and the visualization node.

## Discussion
The obtained results of the project can be considered successful, with the nodes demonstrating effective communication and the real-time transmission of data. As stated in the results section, the decision to employ an MQTT broker was based on the recommendation outlined in the project requirements. Due to the unavailability of a public IP address, the testing was limited to a localhost environment. However, it is important to note that replacing the localhost address with a public IP address would enable the system to function seamlessly across different internet connections.

Furthermore, RabbitMQ was also tested as an alternative to the MQTT broker. The tests conducted with RabbitMQ on a localhost demonstrated its compatibility, and it is expected to function appropriately if a public IP address is configured. Nevertheless, considering the project requirement's recommendation, the MQTT broker was ultimately selected for implementation.

### Future work

In terms of future work for this project, several enhancements and expansions can be considered. These include:
 * Integration of Real Sensor Data: Instead of relying on simulated data, incorporating actual sensor data from sensors installed within a house would provide more accurate and realistic information.
 * Database Integration: Storing the collected sensor data in a database, along with timestamps, would enable historical analysis and trend identification. Additionally, it would facilitate efficient data retrieval and querying.
 * Data Export: Implementing functionality to export the collected data to various formats such as files or sending it through email would enable easy sharing and analysis outside of the system.
 * Enhanced Light Sensor: Improving the light sensor's capabilities to dynamically adjust and control light intensity would provide more precise lighting control and energy efficiency.
 * Expansion to Electricity-related Sensors: Incorporating additional sensor nodes related to electricity consumption could enable intelligent energy management within the house. This could involve detecting room occupancy and optimizing electricity delivery to essential components only when needed, leading to energy savings.
 * Development of a Functional Monitoring App: Creating a comprehensive and user-friendly mobile application to monitor the sensor data would provide a convenient interface for users. The app should be responsive, capable of displaying data in graphs and providing additional relevant information. It should also offer control features for lights and other systems within the house.

 By implementing these future enhancements, the project can evolve into a more sophisticated and practical smart home solution, offering improved energy efficiency, enhanced monitoring capabilities, and user-friendly control options.


## Conclusion
In conclusion, the smart home automation project exemplifies the capabilities of IoT in creating intelligent living environments. Through the development of a functional prototype comprising sensor nodes, actuator nodes, and a user-friendly visualization interface, the project successfully addresses energy efficiency, convenience, and user comfort.

The project's system architecture facilitates seamless communication and data exchange, utilizing the MQTT protocol for real-time monitoring. Data simulation and processing techniques generate meaningful sensor readings and enable intelligent decision-making.

The smart home automation project showcases the potential impact of IoT in transforming living spaces into sustainable and convenient environments. As future enhancements are explored, including additional sensor nodes and advanced automation algorithms, smart homes have the potential to become an integral part of everyday life, providing enhanced living experiences.

## References
[1]: What is Java?. "https://www.ibm.com/topics/java". Accessed: Jun 15,2023.
[2]: What is Maven: Here’s What You Need to Know. "https://www.simplilearn.com/tutorials/maven-tutorial/what-is-maven". Accessed: Jun 15,2023.
[3]: What is MQTT and Why it is Important for the Internet of Things. "https://blog.akenza.io/what-is-mqtt". Accessed: June 16, 2023.

