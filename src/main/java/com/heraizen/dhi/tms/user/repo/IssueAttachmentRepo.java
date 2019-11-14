package com.heraizen.dhi.tms.user.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.heraizen.dhi.tms.domain.IssueAttachment;

public interface IssueAttachmentRepo extends MongoRepository<IssueAttachment,String> {

}
