import axios from 'axios';
import { AttendanceEventAPI, WorkHoursAPI, SaveAttendanceEventAPI } from "./dataSources/apiSource";

export const resolvers = {
    Query: {
      getAttendanceEvent: async (parent, {employeeId}, {dataSources}, info) => {
        console.log(employeeId);
        return dataSources.attendanceEventAPI.getAttendanceEvent(employeeId);
      },
      getWorkHours : async (parent, {employeeId, day}, {dataSources}, info) => {
        console.log(employeeId);
        console.log(day);
        return dataSources.workHoursAPI.getWorkHours(employeeId, day)
      }
    },
    Mutation: {
      createAttendanceEvent: async (parent, {input}, {dataSources}, info) => {
        console.log(input);
        return dataSources.saveAttendanceEventAPI.createAttendanceEvent(input)
      },
    },
  };
  