# PQC-secure-communication-simulator
The simulation of Post-Quantum Cryptography , communication with security against quantum attacks . This is the prototype for the basic understanding of communciation mechanism which can scaled and properly implemented with actual Quantum resistance Algorithms..


# PQC Secure Communication Simulator

A Java-based simulation of a Post-Quantum Cryptography (PQC) inspired secure communication system implementing encrypted messaging, digital signatures, attack detection, and hybrid cryptographic workflows.

## Overview

This project demonstrates a conceptual implementation of post-quantum secure communication using simulated versions of Kyber and Dilithium workflows. Due to native binary compatibility limitations of available PQC libraries on Windows systems, the project recreates the fundamental communication architecture using classical cryptographic primitives such as AES and RSA.

The system allows two users to securely communicate using encrypted channels while detecting message tampering attacks during transmission.

---

## Features

* Simulated Kyber-style key encapsulation mechanism
* Simulated Dilithium-style digital signatures
* AES-based encrypted communication
* RSA-based signature generation and verification
* GUI-based sender and receiver applications
* Socket-based real-time communication
* Man-in-the-middle attack simulation module
* Message integrity verification
* Tampering detection system
* Hybrid cryptographic workflow simulation

---

## System Architecture

Sender → Key Exchange → AES Encryption → Digital Signature → Socket Communication → Attack Module → Receiver → Signature Verification → Message Decryption

---

## Technologies Used

* Java
* Java Swing
* Java Cryptography Architecture (JCA)
* AES Encryption
* RSA Digital Signatures
* SHA-256 Hashing
* Socket Programming
* Multithreading

---

## Modules

### KyberModule2

Simulates post-quantum key encapsulation and shared secret generation.

### DilithiumModule

Implements RSA-based signature generation and verification to conceptually mimic Dilithium workflows.

### EncryptionModule

Handles AES encryption and decryption using shared secret derived keys.

### SenderGUI

GUI interface for secure message transmission.

### ReceiverGUI

GUI interface for receiving and verifying encrypted messages.

### AttackModule

Intercepts and optionally tampers with messages to simulate man-in-the-middle attacks.

---

## Security Workflow

1. Shared secret generated using simulated Kyber workflow
2. AES key derived from shared secret
3. Message encrypted using AES
4. Digital signature generated
5. Message transmitted through sockets
6. Receiver verifies integrity
7. Receiver decrypts verified message

---

## Educational Purpose

This project was developed for educational and research-oriented exploration of secure communication architectures and post-quantum cryptographic workflows.

It does not claim to implement production-grade PQC algorithms, but instead focuses on understanding the operational flow of hybrid cryptographic systems.

---

## Future Improvements

* Integration of actual CRYSTALS-Kyber libraries
* Integration of Dilithium implementations
* AES-GCM authenticated encryption
* Secure IV management
* Multi-client communication support
* End-to-end secure key exchange
* Real network deployment
* Advanced attack simulations

---

## Disclaimer

This project is intended for academic and educational purposes only.

