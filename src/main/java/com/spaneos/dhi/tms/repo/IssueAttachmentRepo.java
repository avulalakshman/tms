package com.spaneos.dhi.tms.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spaneos.dhi.tms.domain.IssueAttachment;

public interface IssueAttachmentRepo extends MongoRepository<IssueAttachment,String> {

}
