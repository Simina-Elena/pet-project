package com.acc.petproject.visitor;

import org.springframework.stereotype.Component;

@Component
public interface VisitorService {
    Visitor findVisitorByUsername(String username);
    Visitor updateVisitorInfo(String username, Visitor updatedVisitor);
}
