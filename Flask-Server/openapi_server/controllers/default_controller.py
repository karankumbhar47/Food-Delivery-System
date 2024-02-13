import connexion
from typing import Dict
from typing import Tuple
from typing import Union
import json

from openapi_server.models.delivery_accept_order_request import DeliveryAcceptOrderRequest  # noqa: E501
from openapi_server.models.delivery_accept_order_post_request import DeliveryAcceptOrderPostRequest  # noqa: E501
from openapi_server.models.delivery_person_details import DeliveryPersonDetails # noqa: E501
from openapi_server.models.delivery_drop_request import DeliveryDropRequest  # noqa: E501
from openapi_server.models.food_item import FoodItem  # noqa: E501
from openapi_server.models.food_item_full import FoodItemFull  # noqa: E501
from openapi_server.models.login_request import LoginRequest  # noqa: E501
from openapi_server.models.order import Order  # noqa: E501
from openapi_server.models.place_order_request import PlaceOrderRequest  # noqa: E501
from openapi_server.models.profile import Profile  # noqa: E501
from openapi_server.models.query_request import QueryRequest  # noqa: E501
from openapi_server.models.user_details import UserDetails  # noqa: E501
from openapi_server.models.vendor_details import VendorDetails  # noqa: E501
from openapi_server.models.vendor_add_product_images_request import VendorAddProductImagesRequest  # noqa: E501
from openapi_server.models.vendor_add_product_request import VendorAddProductRequest  # noqa: E501
from openapi_server.models.vendor_change_product_availabile_request import VendorChangeProductAvailabileRequest  # noqa: E501
from openapi_server.models.vendor_edit_product_request import VendorEditProductRequest  # noqa: E501
from openapi_server.models.vendor_get_requested_orders200_response_inner import VendorGetRequestedOrders200ResponseInner  # noqa: E501
from openapi_server import util

import openapi_server.database as database
import openapi_server.utils.basic as basicUtils
import openapi_server.file_storage as store


def check_product_available(id_, count):  # noqa: E501
    """Check Availability

    Check the availability of a specific item by providing its ID and quantity count # noqa: E501

    :param id: ID of the item to check availability for
    :type id: str
    :param count: Quantity count to check availability for
    :type count: int

    :rtype: Union[bool, Tuple[bool, int], Tuple[bool, int, Dict[str, str]]
    """
    database.init()
    try : 
        database.sqlCursor.execute(f'''SELECT is_available, max_quantity 
                                   FROM Catalog 
                                   WHERE item_id = "{id_}"
                                   ''')
        result = database.sqlCursor.fetchone()
        if  result:
            if result[0] and result[1] >= count:
                return True
            return False
        else:
            print(f'{id_} doesnot exists')
            return ("Unauthorized", 401)
    except:
        return 500


def confirm_order():  # noqa: E501
    """Confirm Order

    Confirm Order Delivery # noqa: E501

    :param session_id: 
    :type  stringsession_id: str
    :param body: 
    :type body: str

    :rtype: Union[float, Tuple[float, int], Tuple[float, int, Dict[str, str]]
    """

    if connexion.request.is_json:
        order_id = connexion.request.get_json()  # noqa: E501
        session_id = connexion.request.headers.get("sessionId")
        database.open()
        verify_status = database.verify_session_id(session_id)
        if verify_status is None:
            return "Unauthorized", 401
        _, userType = verify_status
        if userType == "Consumer":
            while True:
                otp = basicUtils.generate_otp()
                if not database.check_exists(otp, "otp", "FoodOrder"):
                    break
            database.sqlCursor.execute(f'''SELECT COUNT(*)
                                        FROM FoodOrder 
                                        WHERE order_id = "{order_id}"
                                        ''')
            result = database.sqlCursor.fetchone()
            if result:
                database.sqlCursor.execute(f'''UPDATE FoodOrder 
                                           SET otp = "{otp}" 
                                           WHERE order_id = "{order_id}";
                                           ''')
                database.sqlConnection.commit()
                database.close()
                return f"{otp}", 200
            database.close()
            return  "Not Found", 404
        database.close()
        return  "Unauthorized", 401


def delivery_accept_order( delivery_accept_order_request=None):  # noqa: E501
    """Accept Order

     # noqa: E501

    :param session_id: Session ID of the delivery person
    :type session_id: str
    :param delivery_accept_order_request: 
    :type delivery_accept_order_request: dict | bytes

    :rtype: Union[None, Tuple[None, int], Tuple[None, int, Dict[str, str]]
    """
    if connexion.request.is_json:
        delivery_accept_order_request = DeliveryAcceptOrderRequest.from_dict(connexion.request.get_json())  # noqa: E501
        session_id = connexion.request.headers.get("sessionId")
        database.open()
        verify_status = database.verify_session_id(session_id)
        if verify_status is None:
            return "Unauthorized", 401
        _, userType = verify_status
        if userType == "Other":
            database.sqlCursor.execute(f'''SELECT COUNT(*) 
                                       FROM FoodOrder 
                                       WHERE order_id = "{delivery_accept_order_request.order_id}"
                                       ''')
            result = database.sqlCursor.fetchone()
            database.sqlCursor.execute(f'''UPDATE FoodOrder 
                                       SET status = "accepted", 
                                       delivery_person_id = (SELECT user_id 
                                       FROM Session WHERE session_id = "{session_id}");
                                       ''')
            database.sqlConnection.commit()
            database.close()
            if result:
                return "Accepted", 200
            return "Not Found", 404
        database.close()
        return "Unauthorized", 401
    return "Not Found", 404


def delivery_drop( delivery_drop_request=None):  # noqa: E501
    """Drop Order

     # noqa: E501

    :param session_id: Session ID of the delivery person
    :type session_id: strBhilai, Chhattisgarh
    :param delivery_drop_request: 
    :type delivery_drop_request: dict | bytes

    :rtype: Union[None, Tuple[None, int], Tuple[None, int, Dict[str, str]]
    """
    if connexion.request.is_json:
        delivery_drop_request = DeliveryDropRequest.from_dict(connexion.request.get_json())  # noqa: E501
        session_id = connexion.request.headers.get("sessionId")
        database.open()
        verify_status = database.verify_session_id(session_id)
        if verify_status is None:
            return "Unauthorized", 401
        _, userType = verify_status
        if userType == "Other":
            database.sqlCursor.execute(f'''SELECT otp 
                                       FROM FoodOrder 
                                       WHERE order_id ="{delivery_drop_request.order_id}"
                                       ''')
            result = database.sqlCursor.fetchone()
            database.close()
            print(result)
            if result[0] == delivery_drop_request.otp:
                return "Drop", 200
            return "Incorrect OTP", 401
        database.close()
        return "Unauthorized", 401
    return  "Not Found", 404


def delivery_pick():  # noqa: E501
    """Pickup Order

     # noqa: E501

    :param session_id: Session ID of the delivery person
    :type session_id: str
    :para stringm order_id: ID of the order to be picked up
    :type order_id: str
    """
    
    
    session_id = connexion.request.headers.get("sessionId")
    order_id = connexion.request.args.get('orderId') 
    database.open()
    verify_status = database.verify_session_id(session_id)
    if verify_status is None:
        return ["Unauthorized"], 401
    _, userType = verify_status
    if userType == "Other":
        database.sqlCursor.execute(f'''UPDATE FoodOrder 
                                   SET status = "picked"  
                                   WHERE order_id = "{order_id}";
                                   ''')
        database.sqlCursor.execute(f'''SELECT FoodOrder.items 
                                   FROM FoodOrder INNER JOIN Vendor ON FoodOrder.vendor_id = Vendor.user_id 
                                   WHERE  FoodOrder.delivery_person_id = (SELECT user_id FROM Session 
                                   WHERE session_id = "{session_id}") AND FoodOrder.order_id = "{order_id}" ;
                                   ''')
        result = database.sqlCursor.fetchall()
        result_items = []
        for i in result: 
            result_items.append(eval(i[0])[0])
        # result_string = str(result_items)
        result_string = json.dumps(result_items)
        query = f'''UPDATE  DeliveryPerson 
                SET picked_up_items = COALESCE(picked_up_items, '') || '{result_string}' 
                WHERE user_id IN (SELECT user_id FROM Session WHERE session_id = "{session_id}");
                ''' 
        database.sqlCursor.execute(query)
        query = f'''UPDATE FoodOrder
                SET delivery_person_id = (SELECT user_id 
                                         FROM Session
                                         WHERE session_id = "{session_id}")
            WHERE order_id = '{order_id}';'''
 
        database.sqlCursor.execute(query)
        database.sqlConnection.commit()
        database.close()
        return None, 200
    database.close()
    return None, 401


def delivery_view_accepted_orders():  # noqa: E501
    """Get List of Accepted Orders

     # noqa: E501

    :param session_id: Session ID of the delivery person
    :type session_id: str

    :rtype: Union[List[Order], Tuple[List[Order], int], Tuple[List[Order], int, Dict[str, str]]
    """
    session_id = connexion.request.headers.get("sessionId")
    database.open()
    verify_status = database.verify_session_id(session_id)
    if verify_status is None:
        return ["Unauthorized"], 401
    _, userType = verify_status
    if userType == "Other":
        database.sqlCursor.execute(f'''SELECT order_id 
                                   FROM FoodOrder 
                                   WHERE delivery_person_id IN (SELECT user_id 
                                                                FROM Session 
                                                                WHERE session_id = "{session_id}"
                                                                );
                                ''')
        result = database.sqlCursor.fetchall()
        database.close()
        if result:
            return result
        return [], 204 
    database.close()
    return [], 401


def delivery_view_waiting_orders():  # noqa: E501  # status == complete need to implement
    """View Waiting Orders

     # noqa: E501
   6                             $ref: '#/components/schemas/orderId'

    :param session_id: Session ID of the delivery person
    :type session_id: str

    :rtype: Union[List[Order], Tuple[List[Order], int], Tuple[List[Order], int, Dict[str, str]]
    """
    session_id = connexion.request.headers.get("sessionId")
    database.open()
    verify_status = database.verify_session_id(session_id)
    if verify_status is None:
        return ["Unauthorized"], 401
    _, userType = verify_status
    if userType == "Other":
        database.sqlCursor.execute(f'''SELECT order_id, consumer_id, delivery_location, pickup_location, items 
                                   FROM FoodOrder 
                                   WHERE  delivery_person_id is NULL ;
                                   ''')
        result = database.sqlCursor.fetchall()
        print(result)
        order_list = []
        if len(result):
            for i in result: 
                database.sqlCursor.execute(f'''SELECT username 
                                           FROM Consumer 
                                           WHERE user_id = "{i[1]}"
                                           ''')
                consumer_name = database.sqlCursor.fetchone()[0]
                order_dict = {"customerName": consumer_name, 'deliveryAddress' : i[2], 
                              "orderDetails": i[4], 'orderId': i[0], 'pickupLocation' : i[3]}
                order_list.append(order_dict)
            database.close()
            return order_list
            # return (**Order(result))
        database.close()
        return  [], 204
    database.close()
    return ["Unauthorized"], 401



def get_file():  # noqa: E501
    """Get file by file ID

    Retrieve a file, typically an image   6                             $ref: '#/components/schemas/orderId'
, based on the provided file ID. # noqa: E501

    :param file_id: ID of the file to retrieve
    :type file_id: str
    :param session_id: 
    :type session_id: str

    :rtype: Union[str, Tuple[str, int], Tuple[str, int, Dict[str, str]]
    """
    file_id = connexion.request.headers.get("fileId")
    data = store.get_file(file_id)
    if data:
        return data
    else:
        return ("", 404)


def get_orders():  # noqa: E501
    """get_orders

     # noqa: E501

    :param session_id: 
    :type session_id: str
    :param body: 
    :type body: str

    :rtype: Union[VendorGetRequestedOrders200ResponseInner, Tuple[VendorGetRequestedOrders200ResponseInner, int], Tuple[VendorGetRequestedOrders200ResponseInner, int, Dict[str, str]]
    """
    session_id = connexion.request.headers.get("sessionId")
    order_id = connexion.request.headers.get("orderId")
    database.open()
    verify_status = database.verify_session_id(session_id)
    order_id_exists = database.check_exists(order_id, "order_id", "FoodOrder")
    if verify_status is not None and order_id_exists:
        _, user_type = verify_status
        if user_type == "Consumer":
            database.sqlCursor.execute(f'''SELECT *
                                          FROM FoodOrder
                                          WHERE order_id = "{order_id}"
                                       ''')
            result = database.sqlCursor.fetchone()[0]
            order = VendorGetRequestedOrders200ResponseInner(
                 result[0], result[5],result[6], result[4], result[1], result[3], result[2]
            )
            database.close()
            return order, 200
        database.close()
        return VendorGetRequestedOrders200ResponseInner(), 401, {"error": "Unathorized user"}
    database.close()
    return VendorGetRequestedOrders200ResponseInner(), 401, {"error": "Incorrect orderId or sessionId"}


def get_product(id_):  # noqa: E501
    """Get Product Details

    Retrieve details for a specific item by providing its ID # noqa: E501

    :param id: ID of the item to retrieve details for
    :type id: str

    :rtype: Union[FoodItemFull, Tuple[FoodItemFull, int], Tuple[FoodItemFull, int, Dict[str, str]]
    """
    database.init()
    query = '''
        SELECT Catalog.item_id, Catalog.item_name, Catalog.thumbnail_picture, Catalog.price,
            Vendor.username, Vendor.location, Catalog.current_rating, Catalog.is_available,
            Catalog.max_quantity
            FROM Catalog
            INNER JOIN Vendor ON Catalog.vendor = Vendor.user_id
            WHERE Catalog.item_id = ?'''

    database.sqlCursor.execute(f'{query}', (id_,))
    result = database.sqlCursor.fetchone()

    if result:
        return FoodItemFull(*result)
    return ("Product Not Found", 404)


def get_profile(session_id, body):  # noqa: E501
    """get_profile

    Get information about the another user from username if the current user is allowed to do so. Anyone can see information about all the Vendors, Delivery Persons and the user himself. # noqa: E501

    :param session_id: 
    :type session_id: str
    :param body: 
    :type body: str

    :rtype: Union[Profile, Tuple[Profile, int], Tuple[Profile, int, Dict[str, str]]
    """
    return 'do some magic!'


def login():  # noqa: E501
    """Login to user account

    Get username and password and authenticate the user. Returns sessionId for further requests # noqa: E501

    :param login_request: 
    :type login_request: dict | bytes

    :rtype: Union[str, Tuple[str, int], Tuple[str, int, Dict[str, str]]
    """
    if connexion.request.is_json:
        login_request = LoginRequest.from_dict(connexion.request.get_json())  # noqa: E501
        database.open()
        # Check if the user is valid
        if not database.check_exists(login_request.username,
                                     "username", "Consumer"):
            database.close()
            return ("Username", 403)
        # Get password hash
        database.sqlCursor.execute(f'''SELECT password,user_id 
                                   FROM Consumer WHERE username = "{login_request.username}"
                                   ''')  # noqa: E501
        response = database.sqlCursor.fetchone()
        pwd_hash = response[0]
        user_id = response[1]
        # Verify password
        if not basicUtils.verify_password(login_request.password, pwd_hash):
            database.close()
            return ("Password", 403)
        # Add new session id
        while True:
            sid = basicUtils.generate_uid(40)
            if not database.check_exists(sid, "session_id", "Session"):
                break
        database.sqlCursor.execute(f'''
            INSERT INTO Session (session_id, user_id, valid)
            VALUES ('{sid}', '{user_id}', 1)
                                   ''')
        database.sqlConnection.commit()
        database.close()
        return sid
    return ('Bad Request', 400)


def place_order():  # noqa: E501
    """Place the order

    Place the order from the cart, with item id as key and quantity as value. # noqa: E501

    :param session_id: 
    :type session_id: str
    :param place_order_request: 
    :type place_order_request: dict | bytes

    :rtype: Union[str, Tuple[str, int], Tuple[str, int, Dict[str, str]]
    """

    if connexion.request.is_json:
        database.init()
        session_id = connexion.request.headers.get("sessionId")
        verify_status = database.verify_session_id(session_id)
        if verify_status is None:
            return "Unauthorized", 401
        _, userType = verify_status
        place_order_request = PlaceOrderRequest.from_dict(connexion.request.get_json())  # noqa: E501
        if place_order_request and userType.lower() == 'consumer':
            database.open()
            item_carts = {}
            for  i in place_order_request.item_cart:
                database.sqlCursor.execute(f'''SELECT vendor 
                                           FROM Catalog 
                                           WHERE item_id = "{i.item_id}"
                                           ''')
                result = database.sqlCursor.fetchone()
                if result: 
                    if result[0] in item_carts.keys():
                        item_carts[result[0]].append(i)
                    else: 
                        item_carts[result[0]] = [i]
            order_ids = []
            for key, values in item_carts.items():
                database.sqlCursor.execute(f'''SELECT location 
                                           FROM Vendor 
                                           WHERE user_id = "{key}"
                                           ''')
                location = database.sqlCursor.fetchone()[0]
                while True:
                    order_id = basicUtils.generate_uid(40)
                    if not database.check_exists(order_id, "order_id", "FoodOrder"):
                        break
                database.sqlCursor.execute(f'''SELECT user_id 
                                           FROM Session 
                                           WHERE session_id = "{session_id}"
                                           ''')
                consumer_id = database.sqlCursor.fetchone()[0]
                query =  f'''INSERT INTO FoodOrder (order_id, items, delivery_location, pickup_location, vendor_id,  consumer_id )
                            VALUES ('{order_id}', "{values}", '{place_order_request.location}','{location}','{key}', '{consumer_id}' );'''
                print(query)
                database.sqlCursor.execute(query)
                order_ids.append(order_id)
                database.sqlConnection.commit()
            return ('{order_ids}', 200)   
        return ('Unauthorized', 401)
    return ('Unauthorized', 401)


def put_file():  # noqa: E501
    """Upload a file

    Upload an image to server for referencing elsewhere. # noqa: E501

    :param session_id: 
    :type session_id: str
    :param body: 
    :type body: str

    :rtype: Union[str, Tuple[str, int], Tuple[str, int, Dict[str, str]]
    """
    database.open()
    data = connexion.request.get_data()
    session_id = connexion.request.headers.get("sessionId")
    verify_status = database. verify_session_id(session_id)
    if verify_status is None:
        return "Unauthorized", 401
    _, userType = verify_status
    if data and userType == "Vendor":
        return store.store_file(data)
    else:
        return "Only Vendor can upload files", 401


def query():  # noqa: E501
    """Search for items

    Search for items based on search query and filters (Authentication is not necessary) # noqa: E501

    :param session_id: 
    :type session_id: str
    :param query_request: 
    :type query_request: dict | bytes

    :rtype: Union[List[FoodItem], Tuple[List[FoodItem], int], Tuple[List[FoodItem], int, Dict[str, str]]
    """
    session_id = connexion.request.headers.get("sessionId")
    query_request = connexion.request.headers.get("query")

    if query_request:
        database.init()
        database.sqlCursor.execute(f'''SELECT COUNT(*) 
                                   FROM Session 
                                   WHERE session_id = "{session_id}"
                                   ''')
        count = database.sqlCursor.fetchone()[0]
        if count == 1:
            database.open()
            searchText = query_request.lower()

            where_conditions = f'''
                    LOWER(item_name) LIKE '%{searchText}%' OR LOWER(tags) LIKE '%{searchText}%'
                '''
            query =f'''
                    SELECT item_id, item_name, thumbnail_picture, vendor, price, current_rating, tags
                    FROM Catalog
                    WHERE {where_conditions}'''

            database.sqlCursor.execute(f'{query}')
            result = database.sqlCursor.fetchall()
            if result:
                ItemList = []
                for i in range(len(result)):
                    last_item_list = result[i][-1].split('|')
                    modified_tuple = tuple(list(result[i][:-1]) + [None] + [last_item_list])
                    ItemList.append(FoodItem(*modified_tuple)) 
                return ItemList  
            return ("Product Not Found", 404)

def register():  # noqa: E501
    """Register a new consumer?

    Create a new user account with unique username, strong password for authentication and other user info. # noqa: E501

    :param user_details: 
    :type user_details: dict | bytes

    :rtype: Union[str, Tuple[str, int], Tuple[str, int, Dict[str, str]]
    """
    if connexion.request.is_json:
        database.open()
        user_details = UserDetails.from_dict(connexion.request.get_json())  # noqa: E501
        if (database.check_exists(user_details.username, "username", "Consumer") or  # noqa: E501
            not basicUtils.username_is_valid(user_details.username)):
            database.close()
            return ("Username", 409)
        if (not basicUtils.phone_is_valid(user_details.phone) or
            database.check_exists(
                user_details.phone,
                "phone_number",
                "Consumer")):
            database.close()
            return ("Phone", 409)
        if (user_details.email is None or user_details.email == ""):
            user_details.email = ""
        elif (user_details.email != "" and (not basicUtils.email_is_valid(user_details.email) or
              database.check_exists(user_details.email, "email_id", "Consumer"))):
            database.close()
            return ("Email", 409)
        if (basicUtils.password_is_weak(user_details.password)):
            database.close()
            return ("Password", 409)
        hash_password = basicUtils.hash_password(user_details.password)
        while True:
            uid = basicUtils.generate_uid(40)
            if not database.check_exists(uid, "user_id", "Consumer"):
                break
        while True:
            sid = basicUtils.generate_uid(40)
            if not database.check_exists(sid, "session_id", "Session"):
                break
        database.sqlCursor.execute(f'''
            INSERT INTO Consumer (user_id, username, name, phone_number,
                                  email_id, gender, dob, password)
            VALUES ('{uid}', '{user_details.username}', '{user_details.name}',
                    '{user_details.phone}', '{user_details.email}',
                    '{user_details.gender}', '{user_details.dob}',
                    '{hash_password}');
        ''')
        database.sqlCursor.execute(f'''
            INSERT INTO Session (session_id, user_id, valid)
            VALUES ('{sid}', '{uid}', 1)
                                   ''')
        database.sqlConnection.commit()
        database.close()
        return sid
    return ('', 400)


def update_profile(session_id, profile):  # noqa: E501
    """update_profile

    Update userinfo of the user. # noqa: E501

    :param session_id: 
    :type session_id: str
    :param profile: 
    :type profile: dict | bytes

    :rtype: Union[Profile, Tuple[Profile, int], Tuple[Profile, int, Dict[str, str]]
    """
    if connexion.request.is_json:
        profile = Profile.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'


def vendor_add_product():  # noqa: E501
    """vendor_add_product

     # noqa: E501

    :param session_id: 
    :type session_id: str
    :param vendor_add_product_request: 
    :type vendor_add_product_request: dict | bytes

    :rtype: Union[str, Tuple[str, int], Tuple[str, int, Dict[str, str]]
    """
    session_id = connexion.request.headers.get("sessionId")
    if session_id:
        print(session_id)
    else:
        return ("Session Id missing in headers", 400)
    if connexion.request.is_json:
        database.open()
        verify_status = database.verify_session_id(session_id)
        if verify_status is not None:
            user_id, user_type = verify_status
            if user_type == "Vendor":
                request = VendorAddProductRequest.from_dict(connexion.request.get_json())  # noqa: E501

                while True:
                    item_id = basicUtils.generate_uid(40)
                    if not database.check_exists(item_id, "item_id", "Catalog"):
                        break
                database.sqlCursor.execute(f'''SELECT user_id 
                                           FROM Session 
                                           WHERE session_id = "{session_id}";''')
                result = database.sqlCursor.fetchone()
                database.sqlCursor.execute(f'''SELECT COUNT(*) 
                                           FROM Catalog 
                                           WHERE vendor = "{result[0]}" AND item_name = "{request.name}"
                                           ''')
                result1 = database.sqlCursor.fetchone()
                if result1[0] == 0: 
                    tags = "|".join(request.tags)
                    query = f'''INSERT INTO Catalog (item_id, item_name,
                                                     thumbnail_picture, price, tags,
                                                     max_quantity, vendor )
                                VALUES ('{item_id}', '{request.name}',
                                        '{request.thumbnail}', '{request.price}',
                                        '{tags}',
                                        '{request.max_quantity}', '{user_id}');'''
                    database.sqlCursor.execute(f'{query}')
                    database.sqlConnection.commit()
                    database.close()
                    return (item_id, 200)
                database.close()
                return "Duplicate Product", 401 
            else:
                database.close()
                return ('User is not a vendor', 403)
        database.close()
        return ('Invalid session Id', 403)
    return ('Bad Request', 403)


def vendor_add_product_images(session_id, vendor_add_product_images_request):  # noqa: E501
    """vendor_add_product_images

     # noqa: E501

    :param session_id: 
    :type session_id: str
    :param vendor_add_product_images_request: 
    :type vendor_add_product_images_request: dict | bytes

    :rtype: Union[List[str], Tuple[List[str], int], Tuple[List[str], int, Dict[str, str]]
    """
    if connexion.request.is_json:
        vendor_add_product_images_request = VendorAddProductImagesRequest.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'

def vendor_change_availabile():  # noqa: E501
    """vendor_change_availabile

     # noqa: E501

    :param session_id: 
    :type session_id: str
    :param vendor_change_product_availabile_request: 
    :type vendor_change_product_availabile_request: dict | bytes

    :rtype: Union[None, Tuple[None, int], Tuple[None, int, Dict[str, str]]
    """
    if connexion.request.is_json:
        vendor_change_product_availabile_request = VendorChangeProductAvailabileRequest.from_dict(connexion.request.get_json())  # noqa: E501

def vendor_change_product_availabile():  # noqa: E501
    """vendor_change_product_availabile

     # noqa: E501

    :param session_id: 
    :type session_id: str
    :param vendor_change_product_availabile_request: 
    :type vendor_change_product_availabile_request: dict | bytes

    :rtype: Union[None, Tuple[None, int], Tuple[None, int, Dict[str, str]]
    """
    if connexion.request.is_json:
        vendor_change_product_availabile_request = VendorChangeProductAvailabileRequest.from_dict(connexion.request.get_json())  # noqa: E501
        session_id = connexion.request.headers.get("sessionId")
        database.open()
        verify_status = database.verify_session_id(session_id)
        if verify_status is not None:
            _, user_type = verify_status
            if user_type == "Vendor":
                if database.check_exists(vendor_change_product_availabile_request.item_id, "item_id", "Catalog"):
                    query = f'''UPDATE Catalog 
                    SET is_available = "{vendor_change_product_availabile_request.status}" 
                    WHERE item_id = "{vendor_change_product_availabile_request.item_id}";
                    '''
                    database.sqlCursor.execute(query)
                    database.sqlConnection.commit()
                    database.sqlCursor.close()
                    return  None, 200, {"message":"Successfully changed availability of the product."}
                database.sqlCursor.close()
                return None, 404,  {"error": "itemId not found"}
            database.sqlCursor.close()
            return  None, 403, {"error": "You are not authorized to perform this action."}
        database.sqlCursor.close()
        return   None, 404, {"error": "Session ID does not exist."}
    return  None, 404, {"error": "Nothing in request."} 


def vendor_edit_product():  # noqa: E501
    """vendor_edit_product

     # noqa: E501

    :param session_id: 
    :type session_id: str
    :param vendor_edit_product_request: 
    :type vendor_edit_product_request: dict | bytes

    :rtype: Union[FoodItemFull, Tuple[FoodItemFull, int], Tuple[FoodItemFull, int, Dict[str, str]]
    """
    if connexion.request.is_json:
        vendor_edit_product_request = VendorEditProductRequest.from_dict(connexion.request.get_json())  # noqa: E501
        session_id = connexion.request.headers.get("sessionId")
        database.open()
        verify_status = database.verify_session_id(session_id)
        if verify_status is not None:
            _, user_type = verify_status
            if user_type == "Vendor":
                if database.check_exists(vendor_edit_product_request.item_id, "item_id", "Catalog"):
                    query = f''' UPDATE Catalog 
                    SET  item_name = "{vendor_edit_product_request.name}",
                    max_quantity  =  "{vendor_edit_product_request.max_quantity}",
                    price = "{vendor_edit_product_request.price}",
                    thumbnail_picture = "{vendor_edit_product_request.thumbnail}"
                    WHERE item_id = "{vendor_edit_product_request.item_id}"
                    '''
                    database.sqlCursor.execute(query)
                    database.sqlConnection.commit()
                    database.sqlCursor.execute(f"SELECT * FROM Catalog WHERE item_id = '{vendor_edit_product_request.item_id}'")
                    result = database.sqlCursor.fetchone()
                    database.close()
                    if result: 
                        return FoodItemFull(result[0], result[1], result[2], result[4], 
                                            star_rating=result[8], is_available=result[7],  
                                            max_quantity=result[6], tags= result[3])
                database.close()
                return FoodItemFull(), 401, {"error": "itemId not found"} 
            database.close()
            return FoodItemFull(), 403, {"error": "You are not authorized to perform this action."}
        database.close()
        return FoodItemFull(), 404, {"error": "Session ID does not exist."}
    database.close()
    return  FoodItemFull(), 404, {"error": "Nothing in request."} 



def vendor_get_accepted_orders():  # noqa: E501
    """vendor_get_accepted_orders

     # noqa: E501

    :param session_id: 
    :type session_id: str

    :rtype: Union[List[VendorGetRequestedOrders200ResponseInner], Tuple[List[VendorGetRequestedOrders200ResponseInner], int], Tuple[List[VendorGetRequestedOrders200ResponseInner], int, Dict[str, str]]
    """
    session_id = connexion.request.headers.get("sessionId")
    database.open()
    verify_status = database.verify_session_id(session_id)
    if verify_status is not None:
        _, user_type = verify_status
        if user_type == "Vendor":
            database.sqlCursor.execute(f'''SELECT user_id 
                                          FROM Session
                                          WHERE session_id = "{session_id}"
                                       ''')
            user_id = database.sqlCursor.fetchone()[0]
            query = f'''SELECT * 
                        FROM FoodOrder 
                        WHERE vendor_id = "{user_id}" AND status is accepted 
                    '''
            database.sqlCursor.execute(query)
            result = database.sqlCursor.fetchall()
            accpeted_list = []
            for ele in result:
                request = VendorGetRequestedOrders200ResponseInner(
                    ele[0], ele[5], ele[6], ele[4], ele[1], ele[3], ele[2]
                )
                accpeted_list.append(request)
            database.close()
            return accpeted_list, 200
        database.close()
        return ["Unauthorized Session Id"], 403 
    database.close()
    return ["Session Id Not Found"], 404


def vendor_get_requested_orders():  # noqa: E501
    """vendor_get_requested_orders

     # noqa: E501

    :param session_id: 
    :type session_id: str

    :rtype: Union[List[VendorGetRequestedOrders200ResponseInner], Tuple[List[VendorGetRequestedOrders200ResponseInner], int], Tuple[List[VendorGetRequestedOrders200ResponseInner], int, Dict[str, str]]
    """
    session_id = connexion.request.headers.get("sessionId")
    database.open()
    verify_status = database.verify_session_id(session_id)
    if verify_status is not None:
        _, user_type = verify_status
        if user_type == "Vendor":
            database.sqlCursor.execute(f'''SELECT user_id 
                                          FROM Session
                                          WHERE session_id = "{session_id}"
                                       ''')
            user_id = database.sqlCursor.fetchone()[0]
            query = f'''SELECT * 
                        FROM FoodOrder 
                        WHERE vendor_id = "{user_id}" AND status is NULL 
                    '''
            database.sqlCursor.execute(query)
            result = database.sqlCursor.fetchall()
            print(result)
            requested_list = []
            for ele in result:
                request = VendorGetRequestedOrders200ResponseInner(
                    ele[0], ele[5], ele[6], ele[4], ele[1], ele[3], ele[2]
                )
                requested_list.append(request)
            database.close()
            return requested_list, 200
        database.close()
        return ["Unauthorized Session Id"], 403 
    database.close()
    return ["Session Id Not Found"], 404

def register_vendor():
    if connexion.request.is_json:
        database.open()
        vendor_details = VendorDetails.from_dict(connexion.request.get_json())

        if (database.check_exists(vendor_details.username, "username", "Vendor") or  # noqa: E501
            not basicUtils.username_is_valid(vendor_details.username)):
            database.close()
            return ("Username", 409)

        if (not basicUtils.phone_is_valid(vendor_details.phone) or
            database.check_exists(
                vendor_details.phone,
                "phone_number",
                "Vendor")):
            database.close()
            return ("Phone", 409)

        if (vendor_details.email is None or vendor_details.email == ""):
            vendor_details.email = ""
        elif (not basicUtils.email_is_valid(vendor_details.email) or
              database.check_exists(vendor_details.email, "email_id", "Vendor")):
            database.close()
            return ("Email", 409)

        if (basicUtils.password_is_weak(vendor_details.password)):
            database.close()
            return ("Password", 409)

        hash_password = basicUtils.hash_password(vendor_details.password)
        while True:
            uid = basicUtils.generate_uid(40)
            if not database.check_exists(uid, "user_id", "Vendor"):
                break

        while True:
            sid = basicUtils.generate_uid(40)
            if not database.check_exists(sid, "session_id", "Session"):
                break

        database.sqlCursor.execute(f'''
            INSERT INTO Vendor (user_id, username, name, phone_number,
                                email_id, location, password)
            VALUES ('{uid}', '{vendor_details.username}', '{vendor_details.name}',
                    '{vendor_details.phone}', '{vendor_details.email}',
                    '{vendor_details.location}', '{hash_password}');
        ''')
        database.sqlCursor.execute(f'''
            INSERT INTO Session (session_id, user_id, valid)
            VALUES ('{sid}', '{uid}', 1)
        ''')

        database.sqlConnection.commit()
        database.close()
        return sid
    return ('', 400)


def login_vendor():
    if connexion.request.is_json:
        login_request = LoginRequest.from_dict(connexion.request.get_json())  # noqa: E501
        print(login_request)
        database.open()
        # Check if the user is valid
        if not database.check_exists(login_request.username,
                                     "username", "Vendor"):
            database.close()
            return ("Username", 403)
        # Get password hash
        database.sqlCursor.execute(f'''SELECT password,user_id 
                                   FROM Vendor 
                                   WHERE username = "{login_request.username}"
                                   ''')  # noqa: E501
        response = database.sqlCursor.fetchone()
        pwd_hash = response[0]
        user_id = response[1]
        # Verify password
        if not basicUtils.verify_password(login_request.password, pwd_hash):
            database.close()
            return ("Password", 403)
        # Add new session id
        while True:
            sid = basicUtils.generate_uid(40)
            if not database.check_exists(sid, "session_id", "Session"):
                break
        database.sqlCursor.execute(f'''
            INSERT INTO Session (session_id, user_id, valid)
            VALUES ('{sid}', '{user_id}', 1)
                                   ''')
        database.sqlConnection.commit()
        database.close()
        return sid
    return ('Bad Request', 400)

def register_delivery_person():
    if connexion.request.is_json:
        database.open()
        delivery_person_details = DeliveryPersonDetails.from_dict(connexion.request.get_json())
        if (database.check_exists(delivery_person_details.username, "user_name", "DeliveryPerson") or  # noqa: E501
            not basicUtils.username_is_valid(delivery_person_details.username)):
            database.close()
            return ("Username", 409)

        if (not basicUtils.phone_is_valid(delivery_person_details.phone) or
            database.check_exists(
                delivery_person_details.phone,
                "phone_number",
                "DeliveryPerson")):
            database.close()
            return ("Phone", 409)

        if (delivery_person_details.email is None or delivery_person_details.email == ""):
            delivery_person_details.email = ""
        elif (not basicUtils.email_is_valid(delivery_person_details.email) or
              database.check_exists(delivery_person_details.email, "email_id", "DeliveryPerson")):
            database.close()
            return ("Email", 409)

        if (basicUtils.password_is_weak(delivery_person_details.password)):
            database.close()
            return ("Password", 409)

        hash_password = basicUtils.hash_password(delivery_person_details.password)
        while True:
            uid = basicUtils.generate_uid(40)
            if not database.check_exists(uid, "user_id", "DeliveryPerson"):
                break

        while True:
            sid = basicUtils.generate_uid(40)
            if not database.check_exists(sid, "session_id", "Session"):
                break

        database.sqlCursor.execute(f'''
            INSERT INTO DeliveryPerson (user_id, user_name, name, phone_number,
                                email_id, password)
            VALUES ('{uid}', '{delivery_person_details.username}', '{delivery_person_details.name}',
                    '{delivery_person_details.phone}', '{delivery_person_details.email}',
                    '{hash_password}');
        ''')
        database.sqlCursor.execute(f'''
            INSERT INTO Session (session_id, user_id, valid)
            VALUES ('{sid}', '{uid}', 1)
        ''')

        database.sqlConnection.commit()
        database.close()
        return sid
    return ('', 400)


def login_delivery_person():
    if connexion.request.is_json:
        login_request = LoginRequest.from_dict(connexion.request.get_json())  # noqa: E501
        print(login_request)
        database.open()
        # Check if the user is valid
        if not database.check_exists(login_request.username,
                                     "user_name", "DeliveryPerson"):
            database.close()
            return ("Username", 403)
        # Get password hash
        database.sqlCursor.execute(f'''SELECT password,user_id 
                                   FROM DeliveryPerson 
                                   WHERE user_name = "{login_request.username}"
                                   ''')  # noqa: E501
        response = database.sqlCursor.fetchone()
        pwd_hash = response[0]
        user_id = response[1]
        # Verify password
        if not basicUtils.verify_password(login_request.password, pwd_hash):
            database.close()
            return ("Password", 403)
        # Add new session id
        while True:
            sid = basicUtils.generate_uid(40)
            if not database.check_exists(sid, "session_id", "Session"):
                break
        database.sqlCursor.execute(f'''
            INSERT INTO Session (session_id, user_id, valid)
            VALUES ('{sid}', '{user_id}', 1)
                                   ''')
        database.sqlConnection.commit()
        database.close()
        return sid
    return ('Bad Request', 400)
     
