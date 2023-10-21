package com.unsa.applicants.service;

import java.io.Serializable;
import java.util.List;

import com.unsa.applicants.domain.Applicant;

public interface ApplicantService extends Serializable {

    Applicant saveApplicant(Applicant applicant);
    Applicant findApplicant(String document);
    List<Applicant> findApplicants();
    Applicant updateApplicant(String document, Applicant applicant);
    Applicant deleteApplicant(String document);

}
