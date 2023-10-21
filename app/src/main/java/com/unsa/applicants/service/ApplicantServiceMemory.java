package com.unsa.applicants.service;

import java.util.ArrayList;
import java.util.List;

import com.unsa.applicants.domain.Applicant;

public class ApplicantServiceMemory implements ApplicantService {

    private final List<Applicant> applicants = new ArrayList<>();

    @Override
    public Applicant saveApplicant(Applicant applicant) {
        this.applicants.add(applicant);
        return applicant;
    }

    @Override
    public Applicant findApplicant(String document) {
        return this.applicants.stream()
                .filter(applicant -> applicant.getDocument().equals(document))
                .findFirst().orElse(null);
    }

    @Override
    public List<Applicant> findApplicants() {
        return this.applicants;
    }

    @Override
    public Applicant updateApplicant(String document, Applicant updatedApplicant) {
        this.applicants.replaceAll(applicant -> {
            if (applicant.getDocument().equals(document)) {
                applicant.setName(updatedApplicant.getName());
                applicant.setLastname(updatedApplicant.getLastname());
                applicant.setBirthday(updatedApplicant.getBirthday());
                applicant.setSchool(updatedApplicant.getSchool());
                applicant.setCareer(updatedApplicant.getCareer());
            }
            return applicant;
        });
        return this.applicants.stream()
                .filter(applicant -> applicant.getDocument().equals(document))
                .findFirst().orElse(null);
    }

    @Override
    public Applicant deleteApplicant(String document) {
        Applicant applicantToDelete = this.applicants.stream()
                .filter(applicant -> applicant.getDocument().equals(document))
                .findFirst().orElse(null);
        if (applicantToDelete != null) {
            this.applicants.remove(applicantToDelete);
        }
        return applicantToDelete;
    }

}
