create table time_blocks (
  id uuid primary key,
  user_id uuid not null,
  title varchar(200) not null,
  goal text,
  status varchar(32) not null,
  planned_minutes int not null check (planned_minutes > 0),
  started_at timestamptz,
  completed_at timestamptz,
  created_at timestamptz default now() not null
);