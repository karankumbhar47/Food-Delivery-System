import os
import csv

base_image_path = "../catlog/images/"

csv_files = ["vendor_001.csv", "vendor_002.csv", "vendor_003.csv", "vendor_004.csv", "vendor_005.csv"]

# Function to check if image exists for each row in CSV file
def check_image_exists(csv_file):
    with open(csv_file, 'r') as file:
        reader = csv.DictReader(file)
        for row in reader:
            img_path = row['img_path']
            img_full_path = os.path.join(base_image_path, f"{img_path}.jpg")
            # print(img_full_path)
            if not os.path.exists(img_full_path):
                print(f"Image not found for: {row['name']}")

# Iterate through each CSV file and check image existence
for csv_file in csv_files:
    print(f"Checking images for {csv_file}:")
    check_image_exists(csv_file)
    print("\n")