import os
import random

order_template = 'Order%d;%02d-%s-2014;%s;10;%02d-%s-2014;%02d-%s-2014;true;true'
invoice_template = 'Invoice%d;%02d-%s-2014;Customer%d;%.1f;true;true;%d;%02d-%s-2014;%s'
base_dir = "RetailSystem"
months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug"]
order_threshold = 10
num_invoices = 40
products = {}
sold = {}
with open(os.path.join(base_dir, "products.txt")) as f:
	for p in f:
		(id, _, cost, markup, _, _) = p.split(";")
		products[id] = {}
		products[id]['cost'] = cost
		products[id]['markup'] = markup

current_order = 1
generated_invoices = []
generated_orders = []
for i in range(num_invoices):
	day = random.randint(6, 24)
	month = months[i*len(months)/num_invoices]
	rand_products = []
	total_cost = 0.0
	quantity = random.randint(1,6)
	for p in range(random.randint(1,5)):
		id = 'Product%d' % random.randint(1,len(products))
		rand_products.append(id)
		sold[id] = sold.get(id, 0) + quantity
		total_cost += (
				float(products[rand_products[p]]['cost']) + 
				float(products[rand_products[p]]['markup'])
			) * quantity
	products_string = ';'.join(['%s;%s' % (p, quantity) for p in rand_products])
	generated_invoices.append(
		invoice_template %(
			i+1, day, month, random.randint(1,20), 
			total_cost, len(rand_products), day, 
			month, products_string
		)
	)
	for p in sold:
		if sold[p] > order_threshold:
			generated_orders.append(
				order_template %(
					current_order, day, month, p, day+5, month, day+5, month
				)
			)
			sold[p] = 0
			current_order += 1

with open(os.path.join(base_dir, "invoices.txt"), "w") as f:
	f.write("\n".join(generated_invoices))

with open(os.path.join(base_dir, "orders.txt"), "w") as f:
	f.write("\n".join(generated_orders))
