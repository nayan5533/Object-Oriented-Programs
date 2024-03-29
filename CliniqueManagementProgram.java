/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cliniquemanagementprogram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nayan
 */


class Doctor {
    private String name;
    private String id;
    private String specialization;
    private String availability;
    private List<Patient> appointments;

    public Doctor(String name, String id, String specialization, String availability) {
        this.name = name;
        this.id = id;
        this.specialization = specialization;
        this.availability = availability;
        this.appointments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getAvailability() {
        return availability;
    }

    public List<Patient> getAppointments() {
        return appointments;
    }

    public void addAppointment(Patient patient) {
        appointments.add(patient);
    }
}

class Patient {
    private String name;
    private String id;
    private String mobileNumber;
    private int age;

    public Patient(String name, String id, String mobileNumber, int age) {
        this.name = name;
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public int getAge() {
        return age;
    }
}

class Clinique {
    private List<Doctor> doctors;
    private List<Patient> patients;

    public Clinique() {
        this.doctors = new ArrayList<>();
        this.patients = new ArrayList<>();
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public Doctor findDoctorByName(String name) {
        for (Doctor doctor : doctors) {
            if (doctor.getName().equalsIgnoreCase(name)) {
                return doctor;
            }
        }
        return null;
    }

    public Doctor findDoctorById(String id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId().equalsIgnoreCase(id)) {
                return doctor;
            }
        }
        return null;
    }

    public Doctor findDoctorBySpecialization(String specialization) {
        for (Doctor doctor : doctors) {
            if (doctor.getSpecialization().equalsIgnoreCase(specialization)) {
                return doctor;
            }
        }
        return null;
    }

    public Doctor findDoctorByAvailability(String availability) {
        for (Doctor doctor : doctors) {
            if (doctor.getAvailability().equalsIgnoreCase(availability)) {
                return doctor;
            }
        }
        return null;
    }

    public Patient findPatientByName(String name) {
        for (Patient patient : patients) {
            if (patient.getName().equalsIgnoreCase(name)) {
                return patient;
            }
        }
        return null;
    }

    public Patient findPatientById(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equalsIgnoreCase(id)) {
                return patient;
            }
        }
        return null;
    }

    public Patient findPatientByMobileNumber(String mobileNumber) {
        for (Patient patient : patients) {
            if (patient.getMobileNumber().equals(mobileNumber)) {
                return patient;
            }
        }
        return null;
    }

    public void printDoctorPatientReport() {
        System.out.println("Doctor Patient Report:");
        for (Doctor doctor : doctors) {
            System.out.println("Doctor: " + doctor.getName());
            System.out.println("Specialization: " + doctor.getSpecialization());
            System.out.println("Appointments:");
            for (Patient patient : doctor.getAppointments()) {
                System.out.println("Patient: " + patient.getName());
            }
            System.out.println("------------------------------");
        }
    }

    public String popularSpecialization() {
        Map<String, Integer> specializationCount = new HashMap<>();
        for (Doctor doctor : doctors) {
            String specialization = doctor.getSpecialization();
            specializationCount.put(specialization, specializationCount.getOrDefault(specialization, 0) + 1);
        }
        int maxCount = 0;
        String popularSpecialization = "";
        for (Map.Entry<String, Integer> entry : specializationCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                popularSpecialization = entry.getKey();
            }
        }
        return popularSpecialization;
    }

    public String popularDoctor() {
        Map<String, Integer> doctorCount = new HashMap<>();
        for (Doctor doctor : doctors) {
            String doctorName = doctor.getName();
            doctorCount.put(doctorName, doctorCount.getOrDefault(doctorName, 0) + doctor.getAppointments().size());
        }
        int maxCount = 0;
        String popularDoctor = "";
        for (Map.Entry<String, Integer> entry : doctorCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                popularDoctor = entry.getKey();
            }
        }
        return popularDoctor;
    }
}

public class CliniqueManagementProgram {
    public static void main(String[] args) {
        Clinique clinique = new Clinique();

      
        clinique.addDoctor(new Doctor("Dr. Vitthal", "D001", "Cardiology", "AM"));
        clinique.addDoctor(new Doctor("Dr. Patil", "D002", "Orthopedics", "PM"));
        clinique.addDoctor(new Doctor("Dr. Patel", "D003", "Dermatology", "Both"));

     
        clinique.addPatient(new Patient("Abhishek Bacchan", "P001", "1234567890", 35));
        clinique.addPatient(new Patient("Shahrukh Khan", "P002", "9876543210", 30));
        clinique.addPatient(new Patient("Salman Khan", "P003", "5551234567", 40));

      
        System.out.println("Searching for doctor by name: " + clinique.findDoctorByName("Dr. Vitthal").getId());
        System.out.println("Searching for doctor by specialization: " + clinique.findDoctorBySpecialization("Orthopedics").getId());

       
        System.out.println("Searching for patient by name: " + clinique.findPatientByName("Abhishek Bacchan").getId());
        System.out.println("Searching for patient by mobile number: " + clinique.findPatientByMobileNumber("9876543210").getId());

     
        clinique.printDoctorPatientReport();

        
        System.out.println("Popular Specialization: " + clinique.popularSpecialization());
        System.out.println("Popular Doctor: " + clinique.popularDoctor());
    }
}

