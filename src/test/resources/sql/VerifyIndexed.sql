select index_status, index_status_timestamp
from index_request_site_l
where abc_id_reference = '#12 DIGIT ID#' 
order by index_status_timestamp desc