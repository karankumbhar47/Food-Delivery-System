# Food Delivery System

- Users:
  - Consumer:
    - Login Credentials
    - Name
    - Profile Picture
    - Cart (In MVP it will be stored locally and not synced)
    - History(d)
  - Vendor:
    - Login Credentials
    - Profile Picture
    - Available
    - Location
    - Catalog(d)
    - My orders(d)
    - Active Hours
    - Current status (Open/closed)
    - Pure-Veg/Veg Non-veg
  - Delivery Person:
    - Login Credentials
    - Profile Picture
    - Name
    - Vehicle Details
    - Available
    - Picked up items(d)
  - Catalog:
    - Item id (seq integer)
    - Item
      - Item Add-ons (list of addon ids)
    - Thumbnail picture (base64 encoded string)
    - Is addon
    - Tags
    - Price
    - Vendor
    - Max Quantity
    - Is Available
    - Current rating(d)
    - Images (list of urls)
  - Rating:
    - Item, User
    - Stars
    - Text Review
    - Picture(for bugs)
  - Order:
    - Items
    - Total price
    - Delivery location
    - Consumer, Vendor and Delivery person details
    - Status
    - Payment details

%% - Actions:
%%   - Registration
%%   - CRUD on catalog
%%   - Ordering food:
%%     - Items (quantity)
%%     - Drop location
%%     - Payment Method
%%     - Order Status
%%   - Browse catalog
%%   - Add to favourite (for users)
%%   - Rating
%%   - Payment

## What actions can user take?

### Consumer

- Register/Login*
- Update profile
- Search
- Categories/filters
- Browse products*
- Add to cart (mention quantity)*
- Remove from cart*
- Place order*
- Cancel Order (And a refund policy)
- Make payment*
- Check status
- Confirm Delivery*
- Check History
- Give review/suggestion for food and service
- Schedule orders
- Contact of the delivery man and 
%% - Discount/Coupon system

### Vendor

- Register/Login*
- Update profile
- Search
- Add Items*
- Edit item*
- Change availability of vendor*
- Change availability of item*
- Requested orders*
- Confirm/cancel orders
- View confirmed orders*

### Delivery Person

- Register/Login*
- Update profile
- Change availability*
- View waiting orders*
- Accept order*
- Get list of accepted orders*
- Pickup order*
- Drop Order*
