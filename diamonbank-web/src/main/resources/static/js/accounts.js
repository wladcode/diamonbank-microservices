// Initialize tooltips
const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
const tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl);
});

// Example of a function that could be used to refresh the accounts table
function refreshAccounts() {
    // This is a placeholder - in a real application, this would make an AJAX call
    // to fetch updated account data
    console.log('Refreshing accounts...');
    // location.reload(); // Simple reload for now
}

// Event listener for document ready
document.addEventListener('DOMContentLoaded', function() {
    console.log('Accounts page loaded');
    
    // Add any initialization code here
    
    // Example: Set up a refresh button if you add one
    const refreshBtn = document.getElementById('refresh-accounts');
    if (refreshBtn) {
        refreshBtn.addEventListener('click', refreshAccounts);
    }
});
