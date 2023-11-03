import gql from "graphql-tag";

export const typeDefs = gql`
type AttendanceEvent {
  id: ID!
  employeeId: String!
  timestamp: String!
  eventType: String!
}

type Hours {
  hours: Int
}

type Query {
  getAttendanceEvent(employeeId: String!): [AttendanceEvent]
  getWorkHours(employeeId: String!, day: String!): [Hours]!
}

type Mutation {
  createAttendanceEvent(input: AttendanceEventInput!): AttendanceEvent
}

input AttendanceEventInput {
  id: ID!
  employeeId: String!
  timestamp: String!
  eventType: String!
}
`;
