"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.typeDefs = void 0;
const graphql_tag_1 = __importDefault(require("graphql-tag"));
exports.typeDefs = (0, graphql_tag_1.default) `
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
