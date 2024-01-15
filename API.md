> This is just a highlevel discription of the API for quick designing. It is
> not ment to be used as reference

## User management APIs

### Register (/register)[POST] (done)
  - Request Header:
  - Request Body:
    - Requested Username(username)
    - Password(password)
    - Name(name)
    - Phone number(phone)
    - Email-id(email)
    - Gender(gender)
    - DoB(dob)
  - Response Header:
    - Status 200 OK if username is unique and Password valid
        - Response Body:
            - session-id: string(40);
    - Status 403 Forbidden if username is not unique or Password is invalid
        - Response Body:
          - Username Unique: bool (user_uniq)
          - Password Strength: bool (pass_val)
### Login: (/login)[POST] (done)
  - Request:
    - username
    - password
  - Response:
    - Status 200 OK if username and corresponding password is valid
        - Response Body:
            - session-id: string(40);
    - Status 403 Forbidden if invalid credentials
        - Response Body:

### Get Profile (/profile)[GET] (done)

### Update Profile: (/profile/update)[POST] (done)
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

### Search (/query)[GET] (done)
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

### Get Product Details(/product/id)[GET]
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

### Check availability(/product/id/available)[GET]
  - Request:
    - Item Id
    - count
  - Response:
    - is available

### Get file(/file)[GET]
  - Request:
    - file id
  - Response:
    - file (only image probably)

### Put file(/file)[PUT]

### Add to cart
  - (Response 501 Not Implemented)

### Place order(/order/place)[POST]
  - Request:
    - {item id as key and quantity as value}
    - Location
  - Response
    - Order id

### Confirm Delivery(/order/confirm)[POST]
  - Request:
    - order-id
  - Response:
    - OTP


### Payment method
  - (Response 501 Not Implemented)

### Check status (501)
  - Request:
    - order id
  - Response:
    - status

### Get History (501)
### Send Review (501)
### Get contact info (501)

## Vendor APIs

### Add Item (/venndor/product/add) [POST]
- Request:
    - Item name (required)
    - Thumbnail picture (string in base64, optional)
    - Price (integer) (required)
    - max quantity (optional)
    - image urls (array of strings, optional)
- Response:
  - "200 OK"
    - item-id
  - "Forbidden"

### Edit Item (/vendor/product/id/edit) [POST]
- Request:
    - item-id (required)
    - Item name (optional)
    - Thumbnail picture (string in base64, optional)
    - Price (integer) (optional)
    - max quantity (optional)
- Response:
  - "200 OK"
    - nothing or update item (your call)
  - "Forbidden"

### Add Item Image (/vendor/product/id/addImages) POST
- Request:
    - item-id (required)
    - image-id
- Response:
  - "200 OK"
    - updated list of image ids
  - "Forbidden"
### Change Item availability (/vendor/product/id/changeAvailable) POST
- Request:
    - item-id (required)
    - session-id
    - new status (bool)
- Response:
  - "200 OK"
  - "Forbidden"
### Change Vendor availability (/vendor/changeAvailable) POST
- Request:
    - session-id
    - new status (bool)
- Response:
  - "200 OK"
  - "Forbidden"

### View requested orders (/vendor/orders/requested)
- Request:
    - session-id
- Response
    - "OK"
        - array of order info
  - "Forbidden"

### Accept order (/vendor/orders/confirm)
  - (Response 501 Not Implemented)(orders will be auto accepted in mvp)

### View accepted orders (/vendor/orders/accepted)
- Request:
    - session-id
- Response
    - "OK"
        - array of order info
  - "Forbidden"

### Get order details (/order)
- Request
    - session-id
- Response
    - "OK"
        - order-id
        - consumer
        - vendor
        - vendor location
        - drop location
        - order price
  - "Forbidden"

## References
https://developer.mozilla.org/en-US/docs/Web/HTTP/Authentication#authentication_schemes
