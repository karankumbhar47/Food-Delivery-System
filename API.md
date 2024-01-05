### Register
  - Request Header:
  - Request Body:
    - Requested Username
    - Password
    - Name
    - Phone number
    - Email-id
    - Gender
    - DoB
  - Response Header:
    - Status 200 OK if username is unique and Password valid
        - Response Body:
            - session-id: string(40);
    - Status 403 Forbidden if username is not unique or Password is invalid
        - Response Body:
          - Username Unique: bool
          - Password Strength: bool
### Login:
  - Request:
    - username
    - password
  - Response:
    - Status 200 OK if username and corresponding password is valid
        - Response Body:
            - session-id: string(40);
    - Status 403 Forbidden if invalid credentials
        - Response Body:

### Update Profile:
  - session-id in header
  - can return 200 or 401
  - Request Body:
    - Username (required - original cannot be changed)
    - Password
    - Name
    - Phone number
    - Email-id
    - Gender
    - DoB

## Common for following requests

- Request Header:
  - session-id
- Response will be 401 Unauthorized if the session id is invalid or expired.

## Consumer APIs

### Search
  - Request
    - query (search query)
    - filters:
      - name: value (name is key, value is decide format)
    - start_from
    - number of results
    - Available items only
  - Response
    - item-id
    - Item name
    - Thumbnail picture
    - Price
    - Vendor name
    - vendor location
    - Star rating
    - Is Available
    - Tags

### Get Product Details
  - Request:
    - item id
  - Response:
    - item-id
    - Item name
    - Thumbnail picture
    - Price
    - Vendor name
    - vendor location
    - Star rating
    - Is Available
    - max quantity
    - image urls

### Get file
  - Request:
    - file id
  - Response:
    - file (only image probably)

### Check availability
  - Request:
    - Item Id
    - count
  - Response:
    - is available

### Add to cart
  - (Response 501 Not Implemented)

### Place order
  - Request:
    - {item id as key and quantity as value}
    - Location
  - Response
    - Order id

### Payment method
  - (Response 501 Not Implemented)

### Check status (501)
  - Request:
    - order id
  - Response:
    - status

### Confirm Delivery
  - Request:
    - order-id
  - Response:
    - OTP

### Get History (501)
### Send Review (501)
### Get contact info (501)

