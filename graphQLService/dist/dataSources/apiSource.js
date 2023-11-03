"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.SaveAttendanceEventAPI = exports.WorkHoursAPI = exports.AttendanceEventAPI = void 0;
const datasource_rest_1 = require("@apollo/datasource-rest");
class AttendanceEventAPI extends datasource_rest_1.RESTDataSource {
    constructor() {
        super();
        this.baseURL = 'http://localhost:8092/api/';
    }
    async getAttendanceEvent(employeeId) {
        try {
            console.log(employeeId);
            const response = await this.get(`attendance-events/${employeeId}`);
            console.log(response.status);
            return response;
        }
        catch (error) {
            throw new Error(`Error fetching attendance event data: ${error.message}`);
        }
    }
}
exports.AttendanceEventAPI = AttendanceEventAPI;
class WorkHoursAPI extends datasource_rest_1.RESTDataSource {
    constructor() {
        super();
        this.baseURL = 'http://localhost:8092/api/';
    }
    async getWorkHours(employeeId, day) {
        try {
            const response = await this.get(`getDayReport/${employeeId}/${day}`);
            return response;
        }
        catch (error) {
            throw new Error(`Error fetching work day event data: ${error.message}`);
        }
    }
}
exports.WorkHoursAPI = WorkHoursAPI;
class SaveAttendanceEventAPI extends datasource_rest_1.RESTDataSource {
    constructor() {
        super();
        this.baseURL = 'http://localhost:8091/api/data'; // Replace with your API base URL
    }
    async createAttendanceEvent(input) {
        try {
            const jsonData = input.match(/\{([^}]*)\}/);
            if (jsonData) {
                // Convert the content to a JSON object
                const jsonObject = JSON.parse(jsonData[0]);
                // Now, jsonObject is a valid JSON object
                console.log(jsonObject);
                console.log('Here');
                console.log(jsonObject);
                const response = await this.post(this.baseURL, jsonObject);
                console.log(response);
                return response;
            }
        }
        catch (error) {
            throw new Error(`Error creating attendance event: ${error.message}`);
        }
    }
}
exports.SaveAttendanceEventAPI = SaveAttendanceEventAPI;
