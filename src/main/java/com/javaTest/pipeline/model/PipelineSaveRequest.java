package com.javaTest.pipeline.model;

import lombok.Builder;

@Builder
public record PipelineSaveRequest(
        String name,
        Long buildId,
        Long deployId
) {
}
