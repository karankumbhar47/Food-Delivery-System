import connexion
from typing import Dict
from typing import Tuple
from typing import Union

from openapi_server.models.delivery_accept_order_request import DeliveryAcceptOrderRequest  # noqa: E501
from openapi_server.models.delivery_drop_request import DeliveryDropRequest  # noqa: E501
from openapi_server.models.food_item import FoodItem  # noqa: E501
from openapi_server.models.food_item_full import FoodItemFull  # noqa: E501
from openapi_server.models.login_request import LoginRequest  # noqa: E501
from openapi_server.models.order import Order  # noqa: E501
from openapi_server.models.place_order_request import PlaceOrderRequest  # noqa: E501
from openapi_server.models.profile import Profile  # noqa: E501
from openapi_server.models.query_request import QueryRequest  # noqa: E501
from openapi_server.models.user_details import UserDetails  # noqa: E501
from openapi_server.models.vendor_add_product_images_request import VendorAddProductImagesRequest  # noqa: E501
from openapi_server.models.vendor_add_product_request import VendorAddProductRequest  # noqa: E501
from openapi_server.models.vendor_change_product_availabile_request import VendorChangeProductAvailabileRequest  # noqa: E501
from openapi_server.models.vendor_edit_product_request import VendorEditProductRequest  # noqa: E501
from openapi_server.models.vendor_get_requested_orders200_response_inner import VendorGetRequestedOrders200ResponseInner  # noqa: E501
from openapi_server import util

import openapi_server.database as database
import openapi_server.utils.basic as basicUtils


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
        database.sqlCursor.execute("SELECT is_available, max_quantity FROM Catalog WHERE item_id = ?", (id_,))
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

def confirm_order(session_id, body):  # noqa: E501
    """Confirm Order

    Confirm Order Delivery # noqa: E501

    :param session_id: 
    :type session_id: str
    :param body: 
    :type body: str

    :rtype: Union[float, Tuple[float, int], Tuple[float, int, Dict[str, str]]
    """

    return 'do some magic!'


def delivery_accept_order(session_id, delivery_accept_order_request=None):  # noqa: E501
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
    return 'do some magic!'


def delivery_drop(session_id, delivery_drop_request=None):  # noqa: E501
    """Drop Order

     # noqa: E501

    :param session_id: Session ID of the delivery person
    :type session_id: str
    :param delivery_drop_request: 
    :type delivery_drop_request: dict | bytes

    :rtype: Union[None, Tuple[None, int], Tuple[None, int, Dict[str, str]]
    """
    if connexion.request.is_json:
        delivery_drop_request = DeliveryDropRequest.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'


def delivery_pick(session_id, order_id):  # noqa: E501
    """Pickup Order

     # noqa: E501

    :param session_id: Session ID of the delivery person
    :type session_id: str
    :param order_id: ID of the order to be picked up
    :type order_id: str

    :rtype: Union[None, Tuple[None, int], Tuple[None, int, Dict[str, str]]
    """
    return 'do some magic!'


def delivery_view_accepted_orders(session_id):  # noqa: E501
    """Get List of Accepted Orders

     # noqa: E501

    :param session_id: Session ID of the delivery person
    :type session_id: str

    :rtype: Union[List[Order], Tuple[List[Order], int], Tuple[List[Order], int, Dict[str, str]]
    """
    return 'do some magic!'


def delivery_view_waiting_orders(session_id):  # noqa: E501
    """View Waiting Orders

     # noqa: E501

    :param session_id: Session ID of the delivery person
    :type session_id: str

    :rtype: Union[List[Order], Tuple[List[Order], int], Tuple[List[Order], int, Dict[str, str]]
    """
    return 'do some magic!'


def get_file(file_id, session_id=None):  # noqa: E501
    """Get file by file ID

    Retrieve a file, typically an image, based on the provided file ID. # noqa: E501

    :param file_id: ID of the file to retrieve
    :type file_id: str
    :param session_id: 
    :type session_id: str

    :rtype: Union[str, Tuple[str, int], Tuple[str, int, Dict[str, str]]
    """
    return 'do some magic!'


def get_orders(session_id, body):  # noqa: E501
    """get_orders

     # noqa: E501

    :param session_id: 
    :type session_id: str
    :param body: 
    :type body: str

    :rtype: Union[VendorGetRequestedOrders200ResponseInner, Tuple[VendorGetRequestedOrders200ResponseInner, int], Tuple[VendorGetRequestedOrders200ResponseInner, int, Dict[str, str]]
    """
    return 'do some magic!'


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
        return  FoodItemFull(*result)
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
            return ("Forbidden", 403)
        # Get password hash
        database.sqlCursor.execute(f'SELECT password FROM Consumer WHERE username = "{login_request.username}"')  # noqa: E501
        pwd_hash = database.sqlCursor.fetchone()[0]
        # Verify password
        if not basicUtils.verify_password(login_request.password, pwd_hash):
            database.close()
            return ("Forbidden", 403)
        # Add new session id
        while True:
            sid = basicUtils.generate_uid(40)
            if not database.check_exists(sid, "session_id", "Session"):
                break
        database.sqlCursor.execute(f'''
            INSERT INTO Session (session_id, user_id, valid)
            VALUES ('{sid}', '{login_request.username}', 1)
                                   ''')
        database.sqlConnection.commit()
        database.close()
        return sid
    return ('Bad Request', 400)


def place_order(session_id, place_order_request):  # noqa: E501
    """Place the order

    Place the order from the cart, with item id as key and quantity as value. # noqa: E501

    :param session_id: 
    :type session_id: str
    :param place_order_request: 
    :type place_order_request: dict | bytes

    :rtype: Union[str, Tuple[str, int], Tuple[str, int, Dict[str, str]]
    """
    if connexion.request.is_json:
        place_order_request = PlaceOrderRequest.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'


def put_file(session_id, body):  # noqa: E501
    """Upload a file

    Upload an image to server for referencing elsewhere. # noqa: E501

    :param session_id: 
    :type session_id: str
    :param body: 
    :type body: str

    :rtype: Union[str, Tuple[str, int], Tuple[str, int, Dict[str, str]]
    """
    return 'do some magic!'


def query(session_id, query_request):  # noqa: E501
    """Search for items

    Search for items based on search query and filters (Authentication is not necessary) # noqa: E501

    :param session_id: 
    :type session_id: str
    :param query_request: 
    :type query_request: dict | bytes

    :rtype: Union[List[FoodItem], Tuple[List[FoodItem], int], Tuple[List[FoodItem], int, Dict[str, str]]
    """
    if connexion.request.is_json:
        query_request = QueryRequest.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'


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
        if (database.check_exists(user_details.username, "username", "Consumer")):  # noqa: E501
            database.close()
            return ("Username", 409)
        if (not basicUtils.phone_is_valid(user_details.phone) or
            database.check_exists(
                user_details.phone,
                "phone_number",
                "Consumer")):
            database.close()
            return ("Phone", 409)
        if (user_details.email is None):
            user_details.email = ""
        elif (not basicUtils.email_is_valid(user_details.email) or
              database.check_exists(user_details.email, "email_id", "Consumer")):
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


def vendor_add_product(session_id, vendor_add_product_request):  # noqa: E501
    """vendor_add_product

     # noqa: E501

    :param session_id: 
    :type session_id: str
    :param vendor_add_product_request: 
    :type vendor_add_product_request: dict | bytes

    :rtype: Union[str, Tuple[str, int], Tuple[str, int, Dict[str, str]]
    """
    if connexion.request.is_json:
        vendor_add_product_request = VendorAddProductRequest.from_dict(connexion.request.get_json())  # noqa: E501
    return 'do some magic!'


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


def vendor_change_availabile(session_id, vendor_change_product_availabile_request):  # noqa: E501
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
    return 'do some magic!'


def vendor_change_product_availabile(session_id, vendor_change_product_availabile_request):  # noqa: E501
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
    return 'do some magic!'


def vendor_edit_product(session_id, vendor_edit_product_request):  # noqa: E501
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
    return 'do some magic!'


def vendor_get_accepted_orders(session_id):  # noqa: E501
    """vendor_get_accepted_orders

     # noqa: E501

    :param session_id: 
    :type session_id: str

    :rtype: Union[List[VendorGetRequestedOrders200ResponseInner], Tuple[List[VendorGetRequestedOrders200ResponseInner], int], Tuple[List[VendorGetRequestedOrders200ResponseInner], int, Dict[str, str]]
    """
    return 'do some magic!'


def vendor_get_requested_orders(session_id):  # noqa: E501
    """vendor_get_requested_orders

     # noqa: E501

    :param session_id: 
    :type session_id: str

    :rtype: Union[List[VendorGetRequestedOrders200ResponseInner], Tuple[List[VendorGetRequestedOrders200ResponseInner], int], Tuple[List[VendorGetRequestedOrders200ResponseInner], int, Dict[str, str]]
    """
    return 'do some magic!'
