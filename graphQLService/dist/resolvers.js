"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.resolvers = void 0;
exports.resolvers = {
    Query: {
        getAttendanceEvent: async (parent, { employeeId }, { dataSources }, info) => {
            console.log(employeeId);
            return dataSources.attendanceEventAPI.getAttendanceEvent(employeeId);
        },
        getWorkHours: async (parent, { employeeId, day }, { dataSources }, info) => {
            console.log(employeeId);
            console.log(day);
            return dataSources.workHoursAPI.getWorkHours(employeeId, day);
        }
    },
    Mutation: {
        createAttendanceEvent: async (parent, { input }, { dataSources }, info) => {
            console.log(input);
            return dataSources.saveAttendanceEventAPI.createAttendanceEvent(input);
        },
    },
};
