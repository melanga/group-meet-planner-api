# Group Meet Planner API

Are you tired of the never-ending back-and-forth of scheduling group meetings? "I can only do Tuesdays at 2pm", "Sorry,
I have a dentist appointment then", "What about Wednesdays at 10am?" Sound familiar? Well, say goodbye to the headache
and hello to the Group Meet Planner API.

With this API, you can create a group and invite your colleagues, friends or even your book club members, and let our
application take care of finding the perfect time for your next meeting. No more excuses like "I have to feed my pet
rock" or "I have a prior commitment to nap" - our API will find a time that works for everyone.

## Getting Started

Now that you're excited to start using our API, let's jump in and get started!

## Endpoints

### Authentication

- **POST** `/api/v1/auth/register` - Register a new user. Body should contain `username`, `firstName`, `lastName`,
  and `password`. Returns a JWT token.
- **POST** `/api/v1/auth/login` - Login an existing user. Body should contain `username` and `password`. Returns a JWT
  token.

**Note:** All requests below require a Bearer {token} in the headers

### Groups

- **POST** `/api/v1/group/create` - Create a new group. Body should contain `name` and `description`.
- **GET** `/api/v1/group/owned/{username}` - Get all groups owned by a specific user.
- **GET** `/api/v1/group/joined/{username}` - Get all groups joined by a specific user.
- **GET** `/api/v1/group/get/{groupId}` - Get a specific group by its ID.
- **PUT** `/api/v1/group/update/{groupId}` - Update a specific group. Body should contain `name` and `description`.
- **DELETE** `/api/v1/group/delete/{groupId}` - Delete a specific group by its ID.

### Group Members

- **POST** `/api/v1/group/{groupId}/add/{username}` - Add a user to a specific group.
- **POST** `/api/v1/group/{groupId}/remove/{username}` - Remove a user from a specific group.

### Time Slots

- **POST** `/api/v1/timeSlot/create/{username}/{groupId}` - Create a new time slot for a specific group. Body should
  contain `startTime` and `endTime`.
- **GET** `/api/v1/timeSlot/get/{username}/{groupId}` - Get all time slots for a specific group.
- **PUT** `/api/v1/timeSlot/update/{username}/{groupId}` - Update a specific time slot for a specific group. Body should
  contain `startTime` and `endTime`.
- **DELETE** `/api/v1/timeSlot/delete/{username}/{groupId}` - Delete a specific time slot for a specific group.

## Error Handling

In case of errors, the API will return a JSON object with an `error` field containing a description of the error.
