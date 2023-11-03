import { ApolloServer } from "@apollo/server";
import { startStandaloneServer } from "@apollo/server/standalone";
import { typeDefs } from "./schema";
import { makeExecutableSchema } from "@graphql-tools/schema";
import { resolvers } from "./resolvers";
import { AttendanceEventAPI, WorkHoursAPI, SaveAttendanceEventAPI } from "./dataSources/apiSource";

async function startApolloServer() {
  const server = new ApolloServer({ typeDefs, resolvers });

  const { url } = await startStandaloneServer(server, {
    context: async () => {
      const { cache } = server;

      return {
        dataSources: {
          attendanceEventAPI: new AttendanceEventAPI(),
          workHoursAPI: new WorkHoursAPI(),
          saveAttendanceEventAPI: new SaveAttendanceEventAPI()
        },
      };
    },
  });
  console.log(`
    🚀  Server is running!
    📭  Query at ${url}
  `);
}

startApolloServer();
