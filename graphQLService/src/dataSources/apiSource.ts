import { RESTDataSource } from "@apollo/datasource-rest";

class AttendanceEventAPI extends RESTDataSource {
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
    } catch (error) {
      throw new Error(`Error fetching attendance event data: ${error.message}`);
    }
  }
}

class WorkHoursAPI extends RESTDataSource {
  constructor() {
    super();
    this.baseURL = 'http://localhost:8092/api/';
  }

  async getWorkHours(employeeId, day) {
    try {
      const response = await this.get(`getDayReport/${employeeId}/${day}`);
      return response
    } catch (error) {
      throw new Error(`Error fetching work day event data: ${error.message}`);
    }
  }
}

class SaveAttendanceEventAPI extends RESTDataSource {
    constructor() {
      super();
      this.baseURL = 'http://localhost:8091/api/data'; // Replace with your API base URL
    }
  
    async createAttendanceEvent(input) {
      try {
        console.log(input)
        const response = await this.post(this.baseURL, input);
        console.log(response)
        return response;
      } catch (error) {
        throw new Error(`Error creating attendance event: ${error.message}`);
      }
    }
  }

export { AttendanceEventAPI, WorkHoursAPI, SaveAttendanceEventAPI };
