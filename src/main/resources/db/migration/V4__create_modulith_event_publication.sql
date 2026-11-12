CREATE TABLE event_publication (
    id UUID NOT NULL PRIMARY KEY,
    publication_date TIMESTAMP WITH TIME ZONE NOT NULL,
    listener_id VARCHAR(255) NOT NULL,
    serialized_event VARCHAR(255) NOT NULL,
    event_type VARCHAR(255) NOT NULL,
    completion_date TIMESTAMP WITH TIME ZONE,
    last_resubmission_date TIMESTAMP WITH TIME ZONE,
    completion_attempts INTEGER NOT NULL,
    status VARCHAR(255)
);

CREATE INDEX idx_event_publication_completion_date
    ON event_publication (completion_date);
