import React, { useState } from 'react';
import { routeService } from '../services/routeService';
import RouteSearchForm from '../components/RouteSearch/RouteSearchForm';
import RouteResults from '../components/RouteSearch/RouteResults';
import Loading from '../components/Common/Loading';
import ErrorMessage from '../components/Common/ErrorMessage';

export default function RouteSearchPage() {
    const [routes, setRoutes] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [searchParams, setSearchParams] = useState(null);

    const handleSearch = async (origin, destination, date) => {
        try {
            setLoading(true);
            setError(null);
            setSearchParams({ origin, destination, date });

            const data = await routeService.searchRoutes(origin, destination, date);
            setRoutes(data);
        } catch (err) {
            setError(err.formattedMessage || err.message || 'Failed to search routes');
            setRoutes(null);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div style={{ animation: 'fadeIn 0.2s ease-out' }}>
            <div style={{ marginBottom: 'var(--space-6)' }}>
                <h1>Route Search</h1>
                <p style={{ color: 'var(--gray-600)', fontSize: '0.875rem' }}>
                    Find all valid routes between two locations
                </p>
            </div>

            <RouteSearchForm onSearch={handleSearch} loading={loading} />

            {loading && <Loading message="Searching for routes..." />}

            {error && <ErrorMessage message={error} onRetry={() => handleSearch(searchParams.origin, searchParams.destination)} />}

            {!loading && !error && routes && searchParams && (
                <RouteResults
                    routes={routes}
                    origin={searchParams.origin}
                    destination={searchParams.destination}
                />
            )}
        </div>
    );
}
