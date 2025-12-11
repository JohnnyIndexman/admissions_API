CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TYPE application_status AS ENUM ('PENDING','IN_REVIEW','ACCEPTED','REJECTED');

CREATE TABLE applications (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  applicant_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone VARCHAR(50),
  current_step VARCHAR(100) NOT NULL,
  status application_status NOT NULL DEFAULT 'PENDING',
  program_applied VARCHAR(50),
  submitted_at TIMESTAMP WITH TIME ZONE,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
  deleted BOOLEAN NOT NULL DEFAULT false,
  version BIGINT DEFAULT 0
);

CREATE TABLE reviewer_comments (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  application_id UUID NOT NULL REFERENCES applications(id) ON DELETE CASCADE,
  reviewer VARCHAR(255) NOT NULL,
  comment TEXT NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

CREATE INDEX idx_applications_status ON applications(status);
CREATE INDEX idx_applications_created_at ON applications(created_at);
CREATE INDEX idx_applications_email ON applications((lower(email)));
CREATE INDEX idx_applications_program ON applications(program_applied);
